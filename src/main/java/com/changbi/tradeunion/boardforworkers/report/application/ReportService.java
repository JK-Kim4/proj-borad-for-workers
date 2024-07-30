package com.changbi.tradeunion.boardforworkers.report.application;

import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportResponseDto;
import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportSaveDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {

    Long save(ReportSaveDto dto);

    void updateDescription(ReportSaveDto dto);

    void delete(Long reportId);

    ReportResponseDto findById(Long id);

    List<ReportResponseDto> findAll();

    List<ReportResponseDto> findByMemberId(Long memberId);

    LocalDateTime allocateInChargeAdmin(Long reportId, Long adminId);

    LocalDateTime updateReportStatus(Long reportId, String reportStatus);

    LocalDateTime registerReportReason(Long reportId, String reason);

}
