package com.review.monitoring.MonitoringSystem.monitor.user.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.user.repository.MemberRepository;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {
//    private final JPAMemberRepository jpaMemberRepository;
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
        System.out.println("???" + member.getNickname());
        if ( member == null){
            return null;
        }
        if (!member.getPassword().equals(password)) {
            return null;
        }
        System.out.println("?여기까진 오나?");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(member),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_MANAGER")));
        SecurityContextHolder.getContext().setAuthentication(token);
        System.out.println(member.getNickname());
        return member;
    }
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.out.println(id);
        Member member = memberRepository.selectOne(id);

        if (member == null) {
            throw new UsernameNotFoundException(id);
        }

        return new UserAccount(member);
    }
//    @Override
//    public Member logIn(String id, String password) {
//        Member member = memberRepository.selectOne(id);
//        if(member == null) {
//            return null;
//        }
//        if(!member.getPassword().equals(password)) {
//            return null;
//        }
//        return member;
//    }
//    @Transactional(readOnly = true)
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member member = jpaMemberRepository.findByEmail(email);
//        if (member == null) {
//            member = accountRepository.findByNickname(emailOrNickname);
//        }
//
//        if (account == null) {
//            throw new UsernameNotFoundException(emailOrNickname);
//        }
//
//        return new UserAccount(account);
//    }


    @Override
    @Transactional
    public Member updateMemberInfo(MemberVO updatedVO, MemberVO member) {
        if(updatedVO.getEmail().equals(member.getEmail())
                && updatedVO.getDepartment().equals(member.getDepartment())) {
            return null;
        }

        return memberRepository.update(new Member(
<<<<<<< HEAD
                member.getNickname(), updatedVO.getPassword(),
                updatedVO.getEmail(), Department.valueOf(updatedVO.getDepartment())));
=======
                member.getId(), updatedVO.getPassword(),
                updatedVO.getEmail(), Department.valueOf(member.getDepartment())));
>>>>>>> 6a4fd382bdc4ed348a5200769db1f2d5a7e827cb
    }

    @Override
    @Transactional
    public void delete(String memberId) {
        memberRepository.delete(memberId);
    }


}
