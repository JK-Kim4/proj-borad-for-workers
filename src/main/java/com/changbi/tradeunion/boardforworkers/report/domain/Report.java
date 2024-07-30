package com.changbi.tradeunion.boardforworkers.report.domain;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.ReportStatus;
import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportSaveDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Report {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @Column
    private String reportDescription;

    @Column
    private String reasonForResult;

    @Column
    private boolean isAnonymous;

    @Column
    private Long memberId;

    @Column
    private Long inChargeAdminId;

    @Column
    private LocalDateTime appendDate;

    @Column
    private LocalDateTime updateDate;

    @Builder
    public Report(
            String reportStatus,
            String reportDescription,
            boolean isAnonymous, Long memberId){

        this.reportStatus = ReportStatus.valueOf(reportStatus);
        this.reportDescription = reportDescription;
        this.isAnonymous = isAnonymous;
        this.memberId = memberId;
        this.appendDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();

    }

    public void updateDescription(ReportSaveDto dto) {
        this.reportDescription = dto.getReportDescription();
        this.updateDate = LocalDateTime.now();
    }

    public void allocateInChargeAdmin(Long adminId){
        this.inChargeAdminId = adminId;
        this.updateDate = LocalDateTime.now();
    }

    public void updateReportStatus(String reportStatus){
        this.reportStatus = ReportStatus.valueOf(reportStatus);
        this.updateDate = LocalDateTime.now();
    }

    public void updateReasonForResult(String reasonForResult){
        this.reasonForResult = reasonForResult;
        this.updateDate = LocalDateTime.now();
    }
}
