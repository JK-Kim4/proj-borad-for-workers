package com.changbi.tradeunion.boardforworkers.report.presentation.dto;

import com.changbi.tradeunion.boardforworkers.report.domain.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class ReportResponseDto {

    private Long reportId;
    private String reportStatus;
    private String reportDescription;
    private String reasonForResult;
    private boolean isAnonymous;
    private Long memberId;
    private Long inChargeAdminId;
    private LocalDateTime appendDate;
    private LocalDateTime updateDate;

    @Builder
    public ReportResponseDto(Report report){
        this.reportId = report.getId();
        this.reportStatus = report.getReportStatus().getValue();
        this.reportDescription = report.getReportDescription();
        this.reasonForResult = report.getReasonForResult();
        this.isAnonymous = report.isAnonymous();
        this.memberId = report.getMemberId();
        this.inChargeAdminId = report.getInChargeAdminId();
        this.appendDate = report.getAppendDate();
        this.updateDate = report.getUpdateDate();
    }

}
