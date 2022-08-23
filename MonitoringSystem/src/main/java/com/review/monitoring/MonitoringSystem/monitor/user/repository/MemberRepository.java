package com.review.monitoring.MonitoringSystem.monitor.user.repository;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;

public interface MemberRepository {
    void insert(Member member);
    int existByNickname(String nickname);
    Member selectOne(Long memberId);

    Member selectByNickname(String nickname);
    Member update(Member member);
    void delete(Long userId);
}
