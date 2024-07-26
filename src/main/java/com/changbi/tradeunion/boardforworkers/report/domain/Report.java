package com.changbi.tradeunion.boardforworkers.report.domain;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.ReportStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Report {

    @Id @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @Column
    private String reportDescription;

    @Column
    private String reasonForResult;

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
            Long memberId){

        this.reportStatus = ReportStatus.valueOf(reportStatus);
        this.reportDescription = reportDescription;
        this.memberId = memberId;

    }

}
