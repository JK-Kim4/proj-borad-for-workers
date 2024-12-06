package com.changbi.tradeunion.boardforworkers.complaint.repository;

import com.changbi.tradeunion.boardforworkers.complaint.domain.Complaint;
import com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComplaintRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Complaint complaint) {
        em.persist(complaint);
        em.flush();
        return complaint.getId();
    }

    public Complaint findById(Long id) {
        return em.find(Complaint.class, id);
    }

    public ComplaintResponseDto findResponseDtoById(Long complaintId) {
        String query = "select " +
                            "new com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintResponseDto(c) " +
                        "from Complaint c where c.id = :complaintId";

        return em.createQuery(query, ComplaintResponseDto.class)
                .setParameter("complaintId", complaintId)
                .getSingleResult();
    }

    public void delete(Long complaintId) {
        Complaint complaint = em.find(Complaint.class, complaintId);
        em.remove(complaint);
        em.flush();
    }

    public List<ComplaintResponseDto> findComplaints() {
        String query = "select " +
                            "new com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintResponseDto(c) " +
                        "from Complaint c";
        return em.createQuery(query, ComplaintResponseDto.class).getResultList();
    }

    public List<ComplaintResponseDto> findComplaintsByMemberId(Long memberId){
        String query = "select " +
                            "new com.changbi.tradeunion.boardforworkers.complaint.presentation.dto.ComplaintResponseDto(c) " +
                        "from Complaint c ";

        if(memberId != null){
            query += "where c.memberId = :memberId";
        }

        TypedQuery<ComplaintResponseDto> typedQuery = em.createQuery(query, ComplaintResponseDto.class);

        if(memberId != null){
            typedQuery.setParameter("memberId", memberId);
        }

        return typedQuery.getResultList();
    }
}
