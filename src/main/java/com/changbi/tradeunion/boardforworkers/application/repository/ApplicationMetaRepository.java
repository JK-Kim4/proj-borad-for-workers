package com.changbi.tradeunion.boardforworkers.application.repository;

import com.changbi.tradeunion.boardforworkers.application.domain.ApplicationMeta;
import com.changbi.tradeunion.boardforworkers.application.domain.MetaType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationMetaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(ApplicationMeta meta) {
        em.persist(meta);
    }

    public List<ApplicationMeta> findAll() {
        String jpql = "select am from ApplicationMeta am";
        return em.createQuery(jpql, ApplicationMeta.class).getResultList();
    }

    public ApplicationMeta findByType(MetaType metaType) {
        String jpql = "select am from ApplicationMeta am where am.metaType = :metaType";
        return em.createQuery(jpql, ApplicationMeta.class).setParameter("metaType", metaType).getSingleResult();
    }
}
