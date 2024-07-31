package com.changbi.tradeunion.boardforworkers.complaint.application;

import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintResponseDto;
import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintSaveDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ComplaintService {

    Long save(ComplaintSaveDto dto);

    void updateDescription(ComplaintSaveDto dto);

    void delete(Long complaintId);

    ComplaintResponseDto findById(Long id);

    List<ComplaintResponseDto> findAll();

    List<ComplaintResponseDto> findByMemberId(Long memberId);

    LocalDateTime allocateInChargeAdmin(Long complaintId, Long adminId);

    LocalDateTime updateComplaintStatus(Long complaintId, String complaintStatus);

    LocalDateTime registerComplaintReason(Long complaintId, String reason);

}
