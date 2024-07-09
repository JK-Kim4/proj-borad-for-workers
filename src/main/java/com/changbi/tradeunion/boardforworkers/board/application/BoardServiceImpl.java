package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import com.changbi.tradeunion.boardforworkers.board.exception.BoardDuplicationException;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardListDto;
import com.changbi.tradeunion.boardforworkers.board.repository.BoardRepository;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardSaveDto;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
    private final BoardRepository boardRepository;

    @Override
    public Long save(BoardSaveDto boardDto) {

        Board board = boardDto.toEntity();

        if(this.isAlreadyExistBoardName(board.getBoardName())){
            throw new BoardDuplicationException();
        }

        return boardRepository.save(board);
    }

    @Override
    public void update(BoardSaveDto dto) {
        Board board = boardRepository.findById(dto.getBoardId());
        board.update(dto);
    }

    @Override
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId);
        boardRepository.delete(board);
    }

    @Override
    public List<BoardListDto> findBoards(Pagination pagination) {
        return boardRepository.findBoards(pagination).stream()
                .map(board ->
                        BoardListDto.builder()
                            .board(board)
                        .build())
                .toList();
    }

    @Override
    public BoardDetailDto findById(Long boardId) {
        return BoardDetailDto.builder().board(boardRepository.findById(boardId)).build();
    }

    /*private method*/
    private boolean isAlreadyExistBoardName(String boardName) {
        return boardRepository.isAlreadyExistBoardName(boardName);
    }
}
