package com.changbi.tradeunion.boardforworkers.report.application;

import com.changbi.tradeunion.boardforworkers.report.domain.Report;
import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportResponseDto;
import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportSaveDto;
import com.changbi.tradeunion.boardforworkers.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public Long save(ReportSaveDto dto) {
        Report report = dto.toEntity();
        return reportRepository.save(report);
    }

    @Override
    public void updateDescription(ReportSaveDto dto) {
        Report report = reportRepository.findById(dto.getReportId());
        report.updateDescription(dto);
    }

    @Override
    public void delete(Long reportId) {
        reportRepository.delete(reportId);
    }

    @Override
    public ReportResponseDto findById(Long reportId) {
        return reportRepository.findResponseDtoById(reportId);
    }

    @Override
    public List<ReportResponseDto> findAll() {
        return reportRepository.findReports();
    }

    @Override
    public LocalDateTime allocateInChargeAdmin(Long reportId, Long adminId) {
        Report report = reportRepository.findById(reportId);
        report.allocateInChargeAdmin(adminId);
        return report.getUpdateDate();
    }

    @Override
    public LocalDateTime updateReportStatus(Long reportId, String reportStatus) {
        Report report = reportRepository.findById(reportId);
        report.updateReportStatus(reportStatus);
        return report.getUpdateDate();
    }

    @Override
    public LocalDateTime registerReportReason(Long reportId, String reason) {
        Report report = reportRepository.findById(reportId);
        report.updateReasonForResult(reason);
        return report.getUpdateDate();
    }
}
