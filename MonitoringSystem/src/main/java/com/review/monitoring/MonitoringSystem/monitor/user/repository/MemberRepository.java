package com.review.monitoring.MonitoringSystem.monitor.user.repository;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;

public interface MemberRepository {
    void insert(Member member);
    int existById(Long id);
    Member selectOne(Long memberId);
    Member update(Member member);

    void delete(Long userId);
}
