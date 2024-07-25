package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.presentation.dto.*;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;

import java.util.List;

public interface BoardService {

    public Long save(BoardSaveDto boardDto);

    public Long savePost(PostSaveDto postSaveDto);

    public void update(BoardSaveDto dto);

    public void updatePost(PostSaveDto dto);

    public void delete(Long boardId);

    public void deletePost(Long postId);

    public List<BoardListDto> findBoards();

    public List<BoardListDto> findBoardsForClient();

    public List<PostListDto> findPosts(Long boardId, Pagination pagination);

    public List<PostListDto> findPostsForClient(Long boardId, Pagination pagination);

    public List<PostListDto> findPostsByMemberId(Long memberId);

    public BoardDetailDto findById(Long boardId);

    public PostDetailDto findPostById(Long postId, String type);

    public Long findAuthorIdByPostId(Long postId);

    public PostDetailDto findMostRecentNoticePost();

    public PostDetailDto findMostPopularPost();

    public BoardDetailDto findByBoardName(String boardName);

    public int updatePostRecommendCount(Long postId);

    Pagination getPostPagingInfo(Integer pageNum, Integer pageSize);
}
