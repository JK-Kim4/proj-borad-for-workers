package com.changbi.tradeunion.boardforworkers.board.presentation;

import com.changbi.tradeunion.boardforworkers.board.application.BoardServiceImpl;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardSaveDto;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    @PostMapping("/save")
    public ResponseEntity<ResultDto> save(@RequestBody BoardSaveDto dto) {

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(boardService.save(dto))
                .build());
    }

    @PostMapping("/update/{boardId}")
    public ResponseEntity<ResultDto> update(
            @PathVariable(name = "boardId") Long boardId,
            @RequestBody BoardSaveDto dto) {

        boardService.update(dto);

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                .build());
    }

    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<ResultDto> delete(
            @PathVariable(name = "boardId") Long boardId) {

        boardService.delete(boardId);

        return ResponseEntity.ok(ResultDto.builder()
                .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                .build());
    }

    @GetMapping("/boards")
    public ResponseEntity<ResultDto> findBoards(
            @RequestParam(name = "pageNum", required = false) Integer pageNum,
            @RequestParam(name = "pageSize", required = false) Integer pageSize){

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(boardService.findBoards())
                .build());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ResultDto> findBoardById(
            @PathVariable("boardId") Long boardId){

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(boardService.findById(boardId))
                .build());
    }


}
