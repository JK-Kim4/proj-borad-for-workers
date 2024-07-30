package com.changbi.tradeunion.boardforworkers.report.presentation.dto;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.ReportStatus;
import com.changbi.tradeunion.boardforworkers.report.domain.Report;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ReportSaveDto {

    private Long reportId;
    private String  reportStatus;
    private String reportDescription;
    private String reasonForResult;
    private Long memberId;
    private boolean isAnonymous;
    private Long inChargeAdminId;

    public Report toEntity(){
        return Report.builder()
                .isAnonymous(this.isAnonymous)
                .memberId(memberId)
                .reportStatus(ReportStatus.REGISTRATION.name())
                .reportDescription(this.reportDescription)
                .build();
    }
}
