package com.review.monitoring.MonitoringSystem.monitor.user.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;

public interface MemberService {
    int checkDuplicateMember(String id);
    void register(Member member);

    Member getMember(String id);
    Member logIn(String id, String password);
    Member updateMemberInfo(MemberVO memberVO, MemberVO user);

    void delete(String memberId);
}
