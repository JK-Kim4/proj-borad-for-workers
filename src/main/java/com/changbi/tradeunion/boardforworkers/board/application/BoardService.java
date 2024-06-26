package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.common.dto.BoardSaveDto;

public interface BoardService {

    public Long save(BoardSaveDto boardDto);
}
