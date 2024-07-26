package com.changbi.tradeunion.boardforworkers.report.application;

import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportSaveDto;

import java.time.LocalDateTime;

public interface ReportService {

    void save(ReportSaveDto dto);

    void update(ReportSaveDto dto);

    void delete(ReportSaveDto dto);

    Long allocateInChargeAdmin(Long reportId, Long adminId);

    LocalDateTime updateReportStatus(Long reportId, String reportStatus);

    LocalDateTime registerReportReason(Long reportId, String reason);

}
