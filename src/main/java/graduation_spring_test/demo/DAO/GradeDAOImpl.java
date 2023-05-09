package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Grade.Grade;
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
public class GradeDAOImpl implements GradeDAO{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GradeDAOImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public void addGrade(Grade grade) {
        String sql= "INSERT INTO `UserSelectList` VALUES (?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, grade.getMemberId(), grade.getTermNum(), grade.getClassNum(), grade.getScore());
    }

    @Override
    public void deleteGradeByLec(String cNum) {

    }

    @Override
    public void deleteGradeByMember(String memberId) {

    }

    @Override
    public void getGrade(String cNum) {

    }

    @Override
    public Boolean isExistGrade(Grade grade) {
        String sql = "select COUNT(*) AS count from UserSelectList where UserID = ? AND CNumber LIKE ?";
        int result = jdbcTemplate.query(sql,grade, grade.getMemberId(), grade.getClassNum());
        return null;
    }

    @Override
    public void getAllScore(String memberId) {


    }

    @Override
    public void getMajorScore(String memberId) {

    }

    @Override
    public void getCredit(String memberId) {

    }

    private RowMapper<Grade> gradeRowMapper(){
        public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Member member = new Member();
//            member.setId(rs.getString("ID"));
//            member.setPassword(rs.getString("Pincode"));
//            member.setCompleted_semesters(rs.getInt("Semester"));
//            member.setEnroll_year(rs.getInt("StudentNumber"));
//            member.setMajor_curriculum((Major_curriculum) rs.getObject("Course"));
//            member.setTOEIC_score(rs.getInt("TOEIC_Score"));
//            member.setEng_level((English_level) rs.getObject("EnglishGrade"));
//            return member;
        }
    }
}
