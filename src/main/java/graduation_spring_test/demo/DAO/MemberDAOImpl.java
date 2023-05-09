package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Member.Member;
import graduation_spring_test.demo.global.common_unit.English_level;
import graduation_spring_test.demo.global.common_unit.Major_curriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberDAOImpl implements MemberDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMember(Member member) {
        // 회원 등록 쿼리 실행
        String sql = "INSERT INTO UserInfo (ID, Pincode, Semester, StudentNumber, Course, TOEIC_Score, EnglishGrade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, member.getId(), member.getPassword(), member.getCompleted_semesters(),
                member.getEnroll_year(), member.getMajor_curriculum(), member.getTOEIC_score(), member.getEng_level());
    }

    @Override
    public Member getMemberById(String memberId) {
        // 회원 조회 쿼리 실행
        String sql = "SELECT * FROM UserInfo WHERE ID = ?";
        Member member = jdbcTemplate.queryForObject(sql, new Object[]{memberId}, new MemberRowMapper());
        return member;
    }

    @Override
    public void updateMember(Member member) {
        //회원 수정 쿼리 실행
        String sql = "UPDATE UserInfo SET pincode=?, Semester=?, StudentNumber=?, Course=?, TOEIC_Score=?, EnglishGrade=? WHERE ID=?";
        int result = jdbcTemplate.update(sql, member.getPassword(), member.getCompleted_semesters(),
                member.getEnroll_year(), member.getMajor_curriculum(), member.getTOEIC_score(),
                member.getEng_level(), member.getId());
    }

    @Override
    public void deleteMember(Member member) {
        //회원 탈퇴 쿼리 실행
        String sql = "DELETE FROM UserInfo WHERE ID = ?";
        int result = jdbcTemplate.update(sql, member.getId());
    }

    //MemberRoeMapper 클래스를 통해 Member 객체로 변환
    private static final class MemberRowMapper implements RowMapper<Member> {
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setId(rs.getString("ID"));
            member.setPassword(rs.getString("Pincode"));
            member.setCompleted_semesters(rs.getInt("Semester"));
            member.setEnroll_year(rs.getInt("StudentNumber"));
            member.setMajor_curriculum((Major_curriculum) rs.getObject("Course"));
            member.setTOEIC_score(rs.getInt("TOEIC_Score"));
            member.setEng_level((English_level) rs.getObject("EnglishGrade"));
            return member;
        }
    }

}
