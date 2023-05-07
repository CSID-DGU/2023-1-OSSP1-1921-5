package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDao {

    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Override
    public void addMember(Member member) {
        // 회원 등록 쿼리 실행
        // ...
    }

    @Override
    public Member getMemberById(String memberId) {
        // 회원 조회 쿼리 실행
        // ...
        return member;
    }

    // ...
}
