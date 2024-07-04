package com.changbi.tradeunion.boardforworkers.member.presentation;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import com.changbi.tradeunion.boardforworkers.member.application.MemberServiceImpl;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.PreMemberDto;
import jakarta.validation.constraints.NotNull;
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

        //TODO update Member

        return ResponseEntity.ok(resultDto);
    }

    //회원 정보 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ResultDto> delete(
            @PathVariable(name = "memberId") Long memberId) {
        ResultDto<Long> resultDto = new ResultDto<>();

        //TODO delete Member

        return ResponseEntity.ok(resultDto);
    }


    //회원 전체 목록 조회
    @GetMapping("/members")
    public ResponseEntity<ResultDto> findMembers(
            @NotNull @RequestParam(name = "pageNum") Integer pageNum,
            @NotNull @RequestParam(name = "pageSize") Integer pageSize){
        Pagination pagination = Pagination.builder()
                                                .pageNum(pageNum)
                                                .pageSize(pageSize)
                                            .build();

        ResultDto resultDto =
                ResultDto.builder()
                    .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                    .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                    .data(memberService.findMembers(pagination))
                .build();

        return ResponseEntity.ok(resultDto);
    }

    //회원 상세 정보 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<ResultDto> findById(
            @NotNull @PathVariable(name = "memberId") Long memberId) {

        ResultDto resultDto =
                ResultDto.builder()
                    .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                    .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                    .data(memberService.findById(memberId))
                .build();

        return ResponseEntity.ok(resultDto);
    }


    /*
    * Pre Member Entity 관련
    *
    * */
    @PostMapping("/pre-member/save")
    public ResponseEntity<ResultDto> savePreMember(
            @RequestBody PreMemberDto preMemberDto){

        ResultDto resultDto =
                ResultDto.builder()
                    .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                    .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_INSERT)
                    .data(memberService.savePreMember(preMemberDto))
                .build();

        return ResponseEntity.ok(resultDto);

    }

    @GetMapping("/pre-members")
    public ResponseEntity<ResultDto> findPreMembers(
            //TODO pagination
            /*@NotNull @RequestParam(name = "pageNum") Integer pageNum,
            @NotNull @RequestParam(name = "pageSize") Integer pageSize*/){

        ResultDto resultDto =
                ResultDto.builder()
                    .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                    .resultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT)
                    .data(memberService.findPreMembers())
                .build();

        return ResponseEntity.ok(resultDto);
    }
}
