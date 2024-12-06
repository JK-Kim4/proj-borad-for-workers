package com.changbi.tradeunion.boardforworkers.complaint.presentation;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import com.changbi.tradeunion.boardforworkers.complaint.application.ComplaintServiceImpl;
import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/complaint")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintServiceImpl complaintService;

    @PostMapping("/save")
    public ResponseEntity<ResultDto> saveComplaint(
            @RequestBody ComplaintSaveDto dto){

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(complaintService.save(dto))
                .build());
    }

    @GetMapping("/complaints")
    public ResponseEntity<ResultDto> findComplaints(
            @RequestParam(name = "memberId", required = false) Long memberId){

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                        .data(complaintService.findByMemberId(memberId))
                .build());
    }

}
