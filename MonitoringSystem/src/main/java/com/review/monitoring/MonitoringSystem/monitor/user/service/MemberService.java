package com.review.monitoring.MonitoringSystem.monitor.user.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;

public interface MemberService {
    int checkDuplicateMember(String nickname);
    void register(Member member);

    Member getMember(Long id);

    Member getMemberByName(String nickname);
    Member logIn(String nickname, String password);
    Member updateMemberInfo(MemberVO updatedVO, Member member);
    void delete(Long memberId);
}
