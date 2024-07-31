package com.changbi.tradeunion.boardforworkers.complaint.presentation.dto;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.ComplaintStatus;
import com.changbi.tradeunion.boardforworkers.complaint.domain.Complaint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ComplaintSaveDto {

    private Long complaintId;
    private String  complaintStatus;
    private String complaintDescription;
    private String reasonForResult;
    private Long memberId;
    private boolean isAnonymous;
    private Long inChargeAdminId;

    public Complaint toEntity(){
        return Complaint.builder()
                .isAnonymous(this.isAnonymous)
                .memberId(memberId)
                .complaintStatus(ComplaintStatus.REGISTRATION.name())
                .complaintDescription(this.complaintDescription)
                .build();
    }
}
