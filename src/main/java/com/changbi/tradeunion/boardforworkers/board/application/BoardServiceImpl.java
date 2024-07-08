package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardListDto;
import com.changbi.tradeunion.boardforworkers.common.dto.BoardSaveDto;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;

import java.util.List;

public class BoardServiceImpl implements BoardService{

    @Override
    public Long save(BoardSaveDto boardDto) {
        return null;
    }

    @Override
    public void update(BoardSaveDto dto) {

    }

    @Override
    public void delete(Long boardId) {

    }

    @Override
    public List<BoardListDto> findBoards(Pagination pagination) {
        return List.of();
    }

    @Override
    public BoardDetailDto findBoardById(Long boardId) {
        return null;
    }
}
