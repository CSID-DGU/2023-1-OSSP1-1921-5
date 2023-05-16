package graduation_spring_test.demo.domain.Member.service;

import graduation_spring_test.demo.domain.Member.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService {
    void register(Member member);  // 회원 가입
    Member getMemberById(String memberId);  // 회원 조회

    void updateMember(Member member); // 회원 수정

    void findPassword(String memberId, String securityCode, String newPassword); //비밀번호 찾기

    void deleteMember(Member member); // 회원 탈퇴(삭제)

    //보안 코드를 이메일로 전송하는 메서드
    void sendSecurityCodeToEmail(String memberId);
}
