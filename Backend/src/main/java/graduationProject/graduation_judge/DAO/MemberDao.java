package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.domain.Member.Member;

public interface MemberDao {
    Member addMember(Member member);  // 회원 등록
    Member getMemberById(String memberId);  // 회원 조회
    void updateMember(Member member); //회원 수정

    void deleteMember(Member member); //회원 탈퇴
}
