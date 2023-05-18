package graudationProject.graduation_judge.DAO;

import graudationProject.graduation_judge.domain.Member.Member;

public interface MemberDao {
    void addMember(Member member);  // 회원 등록
    Member getMemberById(String memberId);  // 회원 조회
    // ...
}
