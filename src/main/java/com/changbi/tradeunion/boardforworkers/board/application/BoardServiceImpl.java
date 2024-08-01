package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.domain.Attachment;
import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import com.changbi.tradeunion.boardforworkers.board.domain.Post;
import com.changbi.tradeunion.boardforworkers.board.domain.Report;
import com.changbi.tradeunion.boardforworkers.board.exception.AlreadyReportedPostException;
import com.changbi.tradeunion.boardforworkers.board.exception.BoardDuplicationException;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.*;
import com.changbi.tradeunion.boardforworkers.board.repository.BoardRepository;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long save(BoardSaveDto boardDto) {

        Board board = boardDto.toEntity();

        if(this.isAlreadyExistBoardName(board.getBoardName())){
            throw new BoardDuplicationException();
        }

        return boardRepository.save(board);
    }

    @Override
    public Long savePost(PostSaveDto postSaveDto) {
        Post post = postSaveDto.toEntity();
        return boardRepository.savePost(post);
    }

    @Override
    public Long saveAttachment(Attachment attachment) {
        return 0l;
    }

    @Override
    public void update(BoardSaveDto dto) {
        Board board = boardRepository.findById(dto.getBoardId());
        board.update(dto);
    }

    @Override
    public void updatePost(PostSaveDto dto) {
            boardRepository.postUpdate(dto);
    }

    @Override
    public void delete(Long boardId) {
        boardRepository.delete(boardId);
    }

    @Override
    public void deletePost(Long postId) {
        boardRepository.deletePost(postId);
    }

    @Override
    public List<BoardListDto> findBoards() {
        List<BoardListDto> boardListDtoList = boardRepository.findBoards().stream()
                .map(board -> BoardListDto
                        .builder()
                        .board(board)
                        .build())
                .toList();

        for(BoardListDto boardListDto : boardListDtoList){
            List<Board> childBoardList = boardRepository.findChildBoardList(boardListDto.getBoardId());
            if(childBoardList != null && !childBoardList.isEmpty()){
                boardListDto.setChildBoardList(childBoardList.stream()
                        .map(board -> BoardListDto.builder()
                                .board(board)
                                .build())
                        .toList());
            }
        }

        return boardListDtoList;
    }

    @Override
    public List<BoardListDto> findBoardsForClient() {

        List<BoardListDto> boardListDtoList = boardRepository.findBoardsForClient().stream()
                .map(board -> BoardListDto
                        .builder()
                        .board(board)
                        .build())
                .toList();

        for(BoardListDto boardListDto : boardListDtoList){
            List<Board> childBoardList = boardRepository.findChildBoardList(boardListDto.getBoardId());
            if(childBoardList != null && !childBoardList.isEmpty()){
                boardListDto.setChildBoardList(childBoardList.stream()
                        .map(board -> BoardListDto.builder()
                                .board(board)
                                .build())
                        .toList());
            }
        }

        return boardListDtoList;
    }

    @Override
    public List<PostListDto> findPosts(Long boardId, Pagination pagination) {
        return boardRepository.findPosts(boardId);
    }

    @Override
    public List<PostListDto> findPostsForClient(Long boardId, Pagination pagination) {
        return boardRepository.findPostsForClient(boardId, pagination);
    }

    @Override
    public List<PostListDto> findPostsByMemberId(Long memberId) {
        return boardRepository.findPostsByMemberId(memberId);
    }

    @Override
    public BoardDetailDto findById(Long boardId) {
        BoardDetailDto boardDetailDto = BoardDetailDto.builder().board(boardRepository.findById(boardId)).build();

        if(boardDetailDto.getDepth() > 1){
            BoardDetailDto upperBoardDto = BoardDetailDto.builder().board(boardRepository.findParentBoard(boardDetailDto.getUpperBoardId())).build();
            boardDetailDto.setParentBoard(upperBoardDto);
        }

        if(boardDetailDto.getDepth() == 1){
            List<Board> childBoardList = boardRepository.findChildBoardList(boardId);
            boardDetailDto.setChildBoardList(childBoardList.stream()
                    .map(board -> BoardDetailDto.builder()
                            .board(board)
                            .build())
                    .toList());
        }

        return boardDetailDto;
    }

    @Override
    public PostDetailDto findPostById(Long postId, String type) {

        if(!Objects.isNull(type) && CommonValues.SERVICE_TYPE_CLIENT.equals(type.toUpperCase())){
            this.updatePostReadCount(postId);
        }

        return boardRepository.findPostDetailById(postId);
    }

    @Override
    public Long findAuthorIdByPostId(Long postId) {
        return boardRepository.findAuthorIdByPostId(postId);
    }

    @Override
    public PostDetailDto findMostRecentNoticePost() {
        return boardRepository.findMostRecentNoticePost();
    }

    @Override
    public PostDetailDto findMostPopularPost() {
        return boardRepository.findMostPopularPost();
    }

    @Override
    public BoardDetailDto findByBoardName(String boardName) {
        return BoardDetailDto.builder().board(boardRepository.findByBoardName(boardName)).build();
    }

    @Override
    public int updatePostRecommendCount(Long postId) {
        return boardRepository.updatePostRecommendCount(postId);
    }

    @Override
    public Pagination getPostPagingInfo(Integer pageNum, Integer pageSize) {
        return Pagination.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .totalCount(boardRepository.getPostTotalCount())
                .build();
    }

    @Override
    public Long reportPost(HashMap<String, Long> reportParameter) {
        Report report = Report.builder()
                .post(boardRepository.findPostById(reportParameter.get("postId")))
                .member(memberRepository.findById(reportParameter.get("memberId")))
                .build();
        try {
            report = boardRepository.findReportById(report);
            throw new AlreadyReportedPostException(report.getPostId(), report.getMemberId(), "한번 신고한 게시물은 다시 신고할 수 없습니다.");
        }catch (EmptyResultDataAccessException e){
            return boardRepository.reportPost(report);
        }
    }

    /*private method*/
    private boolean isAlreadyExistBoardName(String boardName) {
        return boardRepository.isAlreadyExistBoardName(boardName);
    }

    private void updatePostReadCount(Long postId) {
        boardRepository.updatePostReadCount(postId);
    }


}
