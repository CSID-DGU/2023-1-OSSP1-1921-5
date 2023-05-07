package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Member.Member;

public interface MemberDao {
    void addMember(Member member);  // 회원 등록
    Member getMemberById(String memberId);  // 회원 조회
    // ...
}
