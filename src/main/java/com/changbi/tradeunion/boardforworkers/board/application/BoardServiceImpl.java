package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.domain.*;
import com.changbi.tradeunion.boardforworkers.board.exception.AlreadyReportedPostException;
import com.changbi.tradeunion.boardforworkers.board.exception.BoardDuplicationException;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.*;
import com.changbi.tradeunion.boardforworkers.board.repository.BoardRepository;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.common.utility.FileUtility;
import com.changbi.tradeunion.boardforworkers.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Long saveComment(CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();
        Long commentId = boardRepository.saveComment(comment);

        if(Objects.isNull(commentSaveDto.getParentCommentId())){comment.updateParentCommentId(commentId);}
        else comment.updateParentCommentId(commentSaveDto.getParentCommentId());

        return commentId;
    }

    @Override
    public Long saveAttachment(MultipartFile multipartFile) {

        Map<String, String> uploadResultMap = FileUtility.uploadMultipartFile(multipartFile);

        Attachment attachment = Attachment.builder()
                .fileOriginalName(uploadResultMap.get("originalFilename"))
                .fileName(uploadResultMap.get("renameFileName"))
                .fileSize(uploadResultMap.get("fileSize"))
                .filePath(uploadResultMap.get("filePath"))
                .build();

        return boardRepository.saveAttachment(attachment);
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
    public CommentDetailDto findCommentById(Long commentId) {
        return boardRepository.findCommentById(commentId);
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

    public void downloadAttachment(Long attachmentId, HttpServletResponse response) {
        Attachment attachment = boardRepository.findAttachmentById(attachmentId);

        File file = new File(attachment.getFilePath());
        try {
            if(file.exists() && file.length() == Long.parseLong(attachment.getFileSize())){
                String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(attachment.getFileOriginalName(), "UTF-8");

                // ContentType 설정
                response.setContentType("application/octet-stream; charset=utf-8");

                // Header 설정
                response.setHeader("Content-Disposition", encodedFilename);

                // ContentLength 설정
                response.setContentLengthLong(file.length());

                BufferedInputStream in = null;
                BufferedOutputStream out = null;

                try {
                    in = new BufferedInputStream(new FileInputStream(file));

                    out = new BufferedOutputStream(response.getOutputStream());

                    byte[] buffer = new byte[4096];
                    int bytesRead=0;

                    while ((bytesRead = in.read(buffer))!=-1) {
                        out.write(buffer, 0, bytesRead);
                    }

                    // 버퍼에 남은 내용이 있다면, 모두 파일에 출력
                    out.flush();
                }finally {
                    in.close();
                    out.close();
                }
            }
        }catch (Exception e){
            logger.error("파일다운로드에러", e);
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
