package com.changbi.tradeunion.boardforworkers.report.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepository {

    @PersistenceContext
    private EntityManager em;
}
