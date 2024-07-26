package com.changbi.tradeunion.boardforworkers.report.application;

import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Override
    public void save(ReportSaveDto dto) {

    }

    @Override
    public void update(ReportSaveDto dto) {

    }

    @Override
    public void delete(ReportSaveDto dto) {

    }

    @Override
    public Long allocateInChargeAdmin(Long reportId, Long adminId) {
        return 0l;
    }

    @Override
    public LocalDateTime updateReportStatus(Long reportId, String reportStatus) {
        return null;
    }

    @Override
    public LocalDateTime registerReportReason(Long reportId, String reason) {
        return null;
    }
}
