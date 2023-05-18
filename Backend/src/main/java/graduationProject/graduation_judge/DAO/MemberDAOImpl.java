package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.domain.Member.Member;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import java.util.Optional;

@Repository
public class MemberDAOImpl implements MemberDao {

    private final EntityManager entityManager;

    public MemberDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member addMember(Member member){
        // 회원 등록 쿼리 실행
        entityManager.persist(member);
        return member;
    }

    @Override
    public Member getMemberById(String memberId) {
        // 회원 조회 쿼리 실행
        String sql = "SELECT * FROM UserInfo WHERE ID = ?";
        Member member = entityManager.find(Member.class, memberId);
        return member;
    }//optional써야하나?

    @Override
    public void updateMember(Member member) {
        //회원 수정 쿼리 실행
        entityManager.merge(member);
    }

    @Override
    public void deleteMember(Member member) {
        //회원 탈퇴 쿼리 실행
        entityManager.remove(member);
    }

}
