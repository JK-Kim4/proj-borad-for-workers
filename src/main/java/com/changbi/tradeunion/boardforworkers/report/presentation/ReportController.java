package com.changbi.tradeunion.boardforworkers.report.presentation;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import com.changbi.tradeunion.boardforworkers.report.application.ReportServiceImpl;
import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportServiceImpl reportService;

    @PostMapping("/save")
    public ResponseEntity<ResultDto> saveReport(
            @RequestBody ReportSaveDto dto){

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(reportService.save(dto))
                .build());
    }

    @GetMapping("/reports")
    public ResponseEntity<ResultDto> findReports(
            @RequestParam(name = "memberId", required = false) Long memberId){

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(reportService.findByMemberId(memberId))
                .build());
    }

}
