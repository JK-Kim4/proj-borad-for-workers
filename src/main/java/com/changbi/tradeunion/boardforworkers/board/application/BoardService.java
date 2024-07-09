package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardListDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardSaveDto;

import java.util.List;

public interface BoardService {

    public Long save(BoardSaveDto boardDto);

    public void update(BoardSaveDto dto);

    public void delete(Long boardId);

    public List<BoardListDto> findBoards();

    public BoardDetailDto findById(Long boardId);

}
