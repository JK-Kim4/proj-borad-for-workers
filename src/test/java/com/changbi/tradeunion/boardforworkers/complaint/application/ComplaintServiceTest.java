package com.changbi.tradeunion.boardforworkers.complaint.application;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.ComplaintStatus;
import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintResponseDto;
import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintSaveDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ActiveProfiles("loc")
public class ComplaintServiceTest {

    @Autowired
    ComplaintServiceImpl reportService;

    final String TEST_REPORT_DESCRIPTION = "테스트 리포트입니다.";
    final Long TEST_REPORT_MEMBER_ID = 1L;
    final String TEST_REPORT_STATUS = ComplaintStatus.PASS.name();

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 리포트_엔티티_테스트 {

        @Nested
        @DisplayName("리포트 등록 테스트")
        class report_save_test{

            @Test @Transactional
            @DisplayName("리포트 엔티티를 등록한다.")
            void save_report_test(){
                ComplaintSaveDto dto = new ComplaintSaveDto();
                dto.setMemberId(TEST_REPORT_MEMBER_ID);
                dto.setComplaintDescription(TEST_REPORT_DESCRIPTION);
                dto.setComplaintStatus(TEST_REPORT_STATUS);

                Long reportId = reportService.save(dto);

                ComplaintResponseDto saveResult = reportService.findById(reportId);

                Assertions.assertNotNull(saveResult);
                Assertions.assertEquals(ComplaintStatus.valueOf(dto.getComplaintStatus()).getValue(), saveResult.getComplaintStatus());
            }
        }

        @Nested
        @DisplayName("리포트 엔티티 조회 테스트")
        class report_entity_select_test{

            final String FIND_REPORT_DESCRIPTION = "아이디로 리포트 찾기";
            final Long FIND_REPORT_MEMBER_ID = 99l;
            final String FIND_REPORT_STATUS = ComplaintStatus.REGISTRATION.name();
            Long reportId;

            @BeforeEach
            void beforeEach(){
                ComplaintSaveDto dto = new ComplaintSaveDto();
                dto.setMemberId(TEST_REPORT_MEMBER_ID);
                dto.setComplaintDescription(TEST_REPORT_DESCRIPTION);
                dto.setComplaintStatus(TEST_REPORT_STATUS);

                reportService.save(dto);
                reportService.save(dto);
                reportService.save(dto);

                ComplaintSaveDto findByIdDto = new ComplaintSaveDto();
                findByIdDto.setMemberId(FIND_REPORT_MEMBER_ID);
                findByIdDto.setComplaintDescription(FIND_REPORT_DESCRIPTION);
                findByIdDto.setComplaintStatus(FIND_REPORT_STATUS);

                reportId = reportService.save(findByIdDto);
            }

            @Test @Transactional
            @DisplayName("등록되어있는 엔티티 리스트를 조회한다.")
            void select_report_test(){
                List<ComplaintResponseDto> reports = reportService.findAll();

                Assertions.assertNotNull(reports);
                Assertions.assertEquals(4, reports.size());
            }

            @Test @Transactional
            @DisplayName("리포트 고유번호로 리포트를 조회한다.")
            void select_report_entity_select_test(){
                ComplaintResponseDto report = reportService.findById(reportId);

                Assertions.assertNotNull(report);
                Assertions.assertEquals(report.getComplaintDescription(), FIND_REPORT_DESCRIPTION);
                Assertions.assertEquals(report.getComplaintStatus(), ComplaintStatus.valueOf(FIND_REPORT_STATUS).getValue());
                Assertions.assertEquals(report.getMemberId(), FIND_REPORT_MEMBER_ID);
            }
        }

        @Nested
        @DisplayName("리포트 정보 수정 테스트")
        class update_report_test{

            final String FIND_REPORT_DESCRIPTION = "아이디로 리포트 찾기";
            final Long FIND_REPORT_MEMBER_ID = 99l;
            final String FIND_REPORT_STATUS = ComplaintStatus.REGISTRATION.name();
            Long reportId;

            final Long UPDATE_ADMIN_ID = 90l;
            final String UPDATE_REPORT_DESCRIPTION = "";
            final String UPDATE_REPORT_STATUS = ComplaintStatus.REGISTRATION.name();

            @BeforeEach
            void beforeEach(){
                ComplaintSaveDto dto = new ComplaintSaveDto();
                dto.setMemberId(TEST_REPORT_MEMBER_ID);
                dto.setComplaintDescription(TEST_REPORT_DESCRIPTION);
                dto.setComplaintStatus(TEST_REPORT_STATUS);

                reportService.save(dto);
                reportService.save(dto);
                reportService.save(dto);

                ComplaintSaveDto findByIdDto = new ComplaintSaveDto();
                findByIdDto.setMemberId(FIND_REPORT_MEMBER_ID);
                findByIdDto.setComplaintDescription(FIND_REPORT_DESCRIPTION);
                findByIdDto.setComplaintStatus(FIND_REPORT_STATUS);

                reportId = reportService.save(findByIdDto);
            }

            @Test @Transactional
            @DisplayName("리포트 처리 담당자를 할당한다.")
            void update_report_test(){
                LocalDateTime allocateDate = reportService.allocateInChargeAdmin(reportId, UPDATE_ADMIN_ID);

                ComplaintResponseDto report = reportService.findById(reportId);

                Assertions.assertNotNull(report);
                Assertions.assertEquals(report.getInChargeAdminId(), UPDATE_ADMIN_ID);
                Assertions.assertEquals(report.getUpdateDate(), allocateDate);
            }

        }

    }
}
