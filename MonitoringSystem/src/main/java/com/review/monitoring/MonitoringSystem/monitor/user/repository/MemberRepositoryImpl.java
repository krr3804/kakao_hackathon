package com.review.monitoring.MonitoringSystem.monitor.user.repository;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final EntityManager em;

    @Override
    public void insert(Member member) {
        em.persist(member);
    }

    @Override
    public int existByNickname(String nickname) {
        return em.createQuery("select m from Member m where m.nickname = :memberNickname")
                .setParameter("memberNickname",nickname)
                .setMaxResults(1)
                .getResultList().size();
    }

    @Override
    public Member selectOne(Long memberId) {
        System.out.println("memberId = " + memberId);
        return em.find(Member.class, memberId);

    }

    @Override
    public Member selectByNickname(String nickname) {
        List<Member> data = em.createQuery("select m from Member m where m.nickname = :nickname ")
                .setParameter("nickname", nickname).setMaxResults(1).getResultList();
        if (data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    @Override
    public Member update(Member member) {
        return em.merge(member);
    }

    @Override
    public void delete(Long memberId) {
        em.remove(memberId);
    }
}
