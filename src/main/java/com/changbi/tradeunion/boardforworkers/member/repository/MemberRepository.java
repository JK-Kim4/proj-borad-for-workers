package com.changbi.tradeunion.boardforworkers.member.repository;

import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByMemberName(String memberEmail) {
        String query = "select m from Member m where m.memberEmail = :memberEmail";

        return em.createQuery(query, Member.class)
                .setParameter("memberEmail", memberEmail)
                .getSingleResult();
    }

    public List<Member> findAll(Pagination pagination) {
        String query = "select m from Member m";

        TypedQuery<Member> typedQuery = em.createQuery(query, Member.class);

        return typedQuery
                .setFirstResult(pagination.getPageNum())
                .setMaxResults(pagination.getPageSize())
                .getResultList();
    }

    public boolean isAlreadyExistMemberName(Member member){
        String query =  "select count(m) > 0 " +
                        "from Member m " +
                        "where m.memberEmail = :memberEmail";
        return em.createQuery(query, Boolean.class)
                .setParameter("memberEmail", member.getMemberEmail())
                .getSingleResult();
    }

}
