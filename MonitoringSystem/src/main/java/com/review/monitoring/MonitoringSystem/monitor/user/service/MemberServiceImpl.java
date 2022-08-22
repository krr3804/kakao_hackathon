package com.review.monitoring.MonitoringSystem.monitor.user.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.user.repository.MemberRepository;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public int checkDuplicateMember(String id) {
        return memberRepository.existById(id);
    }

    @Override
    @Transactional
    public void register(Member member) {
        memberRepository.insert(member);
    }

    @Override
    public Member getMember(String id) {
        return memberRepository.selectOne(id);
    }

    @Override
    public Member logIn(String id, String password) {
        Member member = memberRepository.selectOne(id);
        if(member == null) {
            return null;
        }
        if(!member.getPassword().equals(password)) {
            return null;
        }
        return member;
    }

    @Override
    @Transactional
    public Member updateMemberInfo(MemberVO updatedVO, MemberVO member) {
        if(updatedVO.getEmail().equals(member.getEmail())
                && updatedVO.getDepartment().equals(member.getDepartment())) {
            return null;
        }

        return memberRepository.update(new Member(
                member.getId(), updatedVO.getPassword(),
                updatedVO.getEmail(), Department.valueOf(member.getDepartment())));
    }

    @Override
    @Transactional
    public void delete(String memberId) {
        memberRepository.delete(memberId);
    }
}
