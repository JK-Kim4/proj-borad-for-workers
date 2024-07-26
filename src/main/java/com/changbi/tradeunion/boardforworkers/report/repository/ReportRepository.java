package com.changbi.tradeunion.boardforworkers.report.repository;

import com.changbi.tradeunion.boardforworkers.report.domain.Report;
import com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Report report) {
        em.persist(report);
        em.flush();
        return report.getId();
    }

    public Report findById(Long id) {
        return em.find(Report.class, id);
    }

    public ReportResponseDto findResponseDtoById(Long reportId) {
        String query = "select " +
                            "new com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportResponseDto(r) " +
                        "from Report r where r.id = :reportId";

        return em.createQuery(query, ReportResponseDto.class)
                .setParameter("reportId", reportId)
                .getSingleResult();
    }

    public void delete(Long reportId) {
        Report report = em.find(Report.class, reportId);
        em.remove(report);
        em.flush();
    }

    public List<ReportResponseDto> findReports() {
        String query = "select " +
                            "new com.changbi.tradeunion.boardforworkers.report.presentation.dto.ReportResponseDto(r) " +
                        "from Report r";
        return em.createQuery(query, ReportResponseDto.class).getResultList();
    }
}
