package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.presentation.dto.*;

import java.util.List;

public interface BoardService {

    public Long save(BoardSaveDto boardDto);

    public Long savePost(PostSaveDto postSaveDto);

    public void update(BoardSaveDto dto);

    public void delete(Long boardId);

    public List<BoardListDto> findBoards();

    public BoardDetailDto findById(Long boardId);

    public PostDetailDto findPostById(Long postId);

    public BoardDetailDto findByBoardName(String boardName);
}
