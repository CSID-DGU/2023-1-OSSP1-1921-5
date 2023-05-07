package graduation_spring_test.demo.domain.Member.service;

import graduation_spring_test.demo.domain.Member.Member;

public interface MemberService {
    void register(Member member);  // 회원 가입
    Member getMemberById(String memberId);  // 회원 조회

    void updateMember(); // 회원 수정

    void findPassword(); //비밀번호 찾기


}
