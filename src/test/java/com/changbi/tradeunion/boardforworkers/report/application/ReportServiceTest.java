package com.changbi.tradeunion.boardforworkers.report.application;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.ReportStatus;
import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportResponseDto;
import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportSaveDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("loc")
public class ReportServiceTest {

    @Autowired
    ReportServiceImpl reportService;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 리포트_엔티티_테스트 {

        @Nested
        @DisplayName("리포트 등록 테스트")
        class report_save_test{

            @Test @DisplayName("리포트 엔티티를 등록한다.")
            void save_report_test(){
                ReportSaveDto dto = new ReportSaveDto();
                dto.setMemberId(1L);
                dto.setReportDescription("테스트리포트");
                dto.setReportStatus(ReportStatus.PROPOSE.name());

                Long reportId = reportService.save(dto);

                ReportResponseDto saveResult = reportService.findById(reportId);

                Assertions.assertNotNull(saveResult);
                Assertions.assertEquals(ReportStatus.valueOf(dto.getReportStatus()).getValue(), saveResult.getReportStatus());



            }

        }
    }
}
