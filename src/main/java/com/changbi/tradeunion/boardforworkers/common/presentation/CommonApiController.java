package com.changbi.tradeunion.boardforworkers.common.presentation;

import com.changbi.tradeunion.boardforworkers.board.application.BoardServiceImpl;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class CommonApiController {

    private final BoardServiceImpl boardService;

    @GetMapping("/index-data")
    public ResponseEntity<ResultDto> getIndexData(){
        Map<String, Object> indexData = new HashMap<>();

        indexData.put("recentNotice", boardService.findMostRecentNoticePost());
        indexData.put("popularPost", boardService.findMostPopularPost());

        return ResponseEntity.ok(ResultDto.builder()
                    .data(indexData)
                    .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                    .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                .build());
    }
}
