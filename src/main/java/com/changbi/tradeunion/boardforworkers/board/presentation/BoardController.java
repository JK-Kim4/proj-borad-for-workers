package com.changbi.tradeunion.boardforworkers.board.presentation;

import com.changbi.tradeunion.boardforworkers.board.application.BoardServiceImpl;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardSaveDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostSaveDto;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
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

    @PostMapping("/post/save")
    public ResponseEntity<ResultDto> postSave(@RequestBody PostSaveDto dto) {

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(boardService.savePost(dto))
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

    @PostMapping("/post/update/{postId}")
    public ResponseEntity<ResultDto> postUpdate(
            @PathVariable(name = "postId") Long postId,
            @RequestBody PostSaveDto dto){

        boardService.updatePost(dto);

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

    @DeleteMapping("/post/delete/{postId}")
    public ResponseEntity<ResultDto> deletePost(
            @PathVariable(name = "postId") Long postId) {

        boardService.deletePost(postId);

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                .build());
    }

    @GetMapping("/boards")
    public ResponseEntity<ResultDto> findBoards(
            @RequestParam(name = "pageNum", required = false) Integer pageNum,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "type", required = false) String type){

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(CommonValues.SERVICE_TYPE_ADMIN.equalsIgnoreCase(type)
                                ? boardService.findBoards() : boardService.findBoardsForClient())
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

    @GetMapping("/{boardId}/posts")
    public ResponseEntity<ResultDto> findBoardPosts(
            @PathVariable(name = "boardId") Long boardId,
            @RequestParam(name = "pageNum") Integer pageNum,
            @RequestParam(name = "pageSize") Integer pageSize){

        Pagination pagination = boardService.getPostPagingInfo(pageNum, pageSize);

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(boardService.findPosts(boardId, pagination))
                        .pagination(pagination)
                .build());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ResultDto> findPostDetail(
            @PathVariable(name = "postId") Long postId,
            @RequestParam(name = "type", required = false) String type){

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(boardService.findPostById(postId, type))
                .build());
    }

    @GetMapping("/client/{boardId}/posts")
    public ResponseEntity<ResultDto> findBoardPostsForClients(
            @PathVariable(name = "boardId") Long boardId,
            @RequestParam(name = "pageNum") Integer pageNum,
            @RequestParam(name = "pageSize") Integer pageSize){

        Pagination pagination = boardService.getPostPagingInfo(pageNum, pageSize);

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(boardService.findPostsForClient(boardId, pagination))
                        .pagination(pagination)
                .build());
    }

    @GetMapping("/update/recommend-count/{postId}")
    public ResponseEntity<ResultDto> updateRecommendCount(
            @PathVariable(name = "postId") Long postId){


        return ResponseEntity.ok(ResultDto.builder()
                .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(boardService.updatePostRecommendCount(postId))
                .build());
    }

}
