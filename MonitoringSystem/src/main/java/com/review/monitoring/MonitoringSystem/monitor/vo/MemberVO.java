package com.review.monitoring.MonitoringSystem.monitor.vo;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberVO {
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String department;

    public MemberVO() {

    }

    public static MemberVO create(Member member) {
        MemberVO memberVO = new MemberVO();
        memberVO.setNickname(member.getNickname());
        memberVO.setPassword(member.getPassword());
        memberVO.setEmail(member.getEmail());
        memberVO.setDepartment(member.getDepartment().toString());
        return memberVO;
    }
}
