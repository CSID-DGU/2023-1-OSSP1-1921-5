package graudationProject.graduation_judge.DAO;

import graudationProject.graduation_judge.domain.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class MemberDAOImpl implements MemberDao {

    @Autowired
//private JdbcTemplate jdbcTemplate;

    @Override
    public void addMember(Member member) {
        // 회원 등록 쿼리 실행
        /*String sql = "INSERT INTO UserInfo (ID, Pincode, Semester, StudentNumber, Course, TOEIC_Score, EnglishGrade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, member.getID(), member.getPincode(), member.getSemester(),
                member.getStudentNumber(), member.getCourse(), member.getTOEICScore(), member.getEnglishGrade());*/
    }

    @Override
    public Member getMemberById(String memberId) {
        // 회원 조회 쿼리 실행
        /*String sql = "SELECT * FROM UserInfo WHERE ID = ?";
        Member member = jdbcTemplate.queryForObject(sql, new Object[]{memberId}, new MemberRowMapper());*/
        return member;
    }

    @Override
    public void updateMember(Member member) {
        //회원 수정 쿼리 실행
    }

    @Override
    public void deleteMember(Member member) {
        //회원 탈퇴 쿼리 실행
    }

    //MemberRoeMapper 클래스를 통해 Member 객체로 변환
    /*private static final class MemberRowMapper implements RowMapper<Member>{
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
            Member member = new Member();
            member.setID(rs.getString("ID"));
            member.setPincode(rs.getString("Pincode"));
            member.setSemester(rs.getInt("Semester"));
            member.setStudentNumber(rs.getInt("StudentNumber"));
            member.setCourse(rs.getString("Course"));
            member.setTOEICScore(rs.getInt("TOEIC_Score"));
            member.setEnglishGrade(rs.getInt("EnglishGrade"));
            return member;
        }
    }*/
}
