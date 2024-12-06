package com.changbi.tradeunion.boardforworkers.complaint.application;

import com.changbi.tradeunion.boardforworkers.complaint.domain.Complaint;
import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintResponseDto;
import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintSaveDto;
import com.changbi.tradeunion.boardforworkers.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Override
    public Long save(ComplaintSaveDto dto) {
        Complaint complaint = dto.toEntity();
        return complaintRepository.save(complaint);
    }

    @Override
    public void updateDescription(ComplaintSaveDto dto) {
        Complaint complaint = complaintRepository.findById(dto.getComplaintId());
        complaint.updateDescription(dto);
    }

    @Override
    public void delete(Long complaintId) {
        complaintRepository.delete(complaintId);
    }

    @Override
    public ComplaintResponseDto findById(Long complaintId) {
        return complaintRepository.findResponseDtoById(complaintId);
    }

    @Override
    public List<ComplaintResponseDto> findAll() {
        return complaintRepository.findComplaints();
    }

    @Override
    public List<ComplaintResponseDto> findByMemberId(Long memberId) {
        return complaintRepository.findComplaintsByMemberId(memberId);
    }

    @Override
    public LocalDateTime allocateInChargeAdmin(Long complaintId, Long adminId) {
        Complaint complaint = complaintRepository.findById(complaintId);
        complaint.allocateInChargeAdmin(adminId);
        return complaint.getUpdateDate();
    }

    @Override
    public LocalDateTime updateComplaintStatus(Long complaintId, String complaintStatus) {
        Complaint complaint = complaintRepository.findById(complaintId);
        complaint.updateComplaintStatus(complaintStatus);
        return complaint.getUpdateDate();
    }

    @Override
    public LocalDateTime registerComplaintReason(Long complaintId, String reason) {
        Complaint complaint = complaintRepository.findById(complaintId);
        complaint.updateReasonForResult(reason);
        return complaint.getUpdateDate();
    }
}
