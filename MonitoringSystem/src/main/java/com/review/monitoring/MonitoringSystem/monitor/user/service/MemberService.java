package com.review.monitoring.MonitoringSystem.monitor.user.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;

public interface MemberService {
    int checkDuplicateMember(String nickName);
    void register(Member member);

    Member getMember(Long id);
    Member logIn(Long id, String password);
    Member updateMemberInfo(MemberVO memberVO, MemberVO user);

    void delete(Long memberId);
}
