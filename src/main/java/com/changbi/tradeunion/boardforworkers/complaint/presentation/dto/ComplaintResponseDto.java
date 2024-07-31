package com.changbi.tradeunion.boardforworkers.complaint.presentation.dto;

import com.changbi.tradeunion.boardforworkers.complaint.domain.Complaint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class ComplaintResponseDto {

    private Long complaintId;
    private String complaintStatus;
    private String complaintDescription;
    private String reasonForResult;
    private boolean isAnonymous;
    private Long memberId;
    private Long inChargeAdminId;
    private LocalDateTime appendDate;
    private LocalDateTime updateDate;

    @Builder
    public ComplaintResponseDto(Complaint complaint){
        this.complaintId = complaint.getId();
        this.complaintStatus = complaint.getComplaintStatus().getValue();
        this.complaintDescription = complaint.getComplaintDescription();
        this.reasonForResult = complaint.getReasonForResult();
        this.isAnonymous = complaint.isAnonymous();
        this.memberId = complaint.getMemberId();
        this.inChargeAdminId = complaint.getInChargeAdminId();
        this.appendDate = complaint.getAppendDate();
        this.updateDate = complaint.getUpdateDate();
    }

}
