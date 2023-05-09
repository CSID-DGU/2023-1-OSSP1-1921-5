package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Grade.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
    public Boolean isExistGrade(String cNum) {
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
}
