package com.changbi.tradeunion.boardforworkers.member.presentation;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import com.changbi.tradeunion.boardforworkers.member.application.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private final MemberServiceImpl memberService;

    //신규 회원 계정 등록
    @PostMapping("/save")
    public ResponseEntity<ResultDto> save(@RequestBody MemberSaveDto memberDto) {
        ResultDto<Long> resultDto = new ResultDto<>();

        resultDto.setResultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT);
        resultDto.setResultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_INSERT);
        resultDto.setData(memberService.save(memberDto));

        return ResponseEntity.ok(resultDto);
    }

    //회원 정보 수정
    @PostMapping("/update/{memberId}")
    public ResponseEntity<ResultDto> update(
            @PathVariable Long memberId,
            @RequestBody MemberSaveDto memberDto) {
        ResultDto<Long> resultDto = new ResultDto<>();


        return ResponseEntity.ok(resultDto);
    }
}
