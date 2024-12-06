package com.changbi.tradeunion.boardforworkers.complaint.domain;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.ComplaintStatus;
import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintSaveDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Complaint {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintStatus;

    @Column
    private String complaintDescription;

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
    public Complaint(
            String complaintStatus,
            String complaintDescription,
            boolean isAnonymous, Long memberId){

        this.complaintStatus = ComplaintStatus.valueOf(complaintStatus);
        this.complaintDescription = complaintDescription;
        this.isAnonymous = isAnonymous;
        this.memberId = memberId;
        this.appendDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();

    }

    public void updateDescription(ComplaintSaveDto dto) {
        this.complaintDescription = dto.getComplaintDescription();
        this.updateDate = LocalDateTime.now();
    }

    public void allocateInChargeAdmin(Long adminId){
        this.inChargeAdminId = adminId;
        this.updateDate = LocalDateTime.now();
    }

    public void updateComplaintStatus(String complaintStatus){
        this.complaintStatus = ComplaintStatus.valueOf(complaintStatus);
        this.updateDate = LocalDateTime.now();
    }

    public void updateReasonForResult(String reasonForResult){
        this.reasonForResult = reasonForResult;
        this.updateDate = LocalDateTime.now();
    }
}
