package com.changbi.tradeunion.boardforworkers.report.presentation.dto;

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
    private Long inChargeAdminId;


    public Report toEntity(){
        return Report.builder()
                .memberId(this.memberId)
                .reportStatus(this.reportStatus)
                .reportDescription(this.reportDescription)
                .build();
    }
}
