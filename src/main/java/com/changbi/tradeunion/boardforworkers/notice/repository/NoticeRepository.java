package com.changbi.tradeunion.boardforworkers.notice.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeRepository {

    @PersistenceContext
    private EntityManager em;


}
