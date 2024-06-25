package com.changbi.tradeunion.boardforworkers.member.repository;

import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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

    public Member findByMemberName(String memberName) {
        String query = "select m from Member m where m.memberName = :memberName";

        return em.createQuery(query, Member.class)
                .setParameter("memberName", memberName)
                .getSingleResult();
    }

    public boolean isAlreadyExistMemberName(Member member){
        String query =  "select count(m) > 0 " +
                        "from Member m " +
                        "where m.memberName = :memberName";
        return em.createQuery(query, Boolean.class)
                .setParameter("memberName", member.getMemberName())
                .getSingleResult();
    }

}
