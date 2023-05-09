package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Grade.Grade;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

@Repository
public class GradeDAOImpl implements GradeDAO{
    //private final EntityManager entityManager;

//    public JpaGradeDAO(EntityManager entityManager){
//        this.entityManager = entityManager;
//    }

    @Override
    public void addGrade(Grade grade) {
        String sql= "INSERT INTO `UserSelectList` VALUES (?, ?, ?, ?)";
        //int result = jdbcTemplate.update(sql, grade.getMemberId(), grade.getTermNum(), grade.getClassNum(), grade.getScore());
    }

    @Override
    public void deleteGradeByLec(String cNum) {

    }

    @Override
    public void deleteGradeByMember(String memberId) {

    }

    @Override
    public String getGrade(String cNum) {
        return null;
    }

    @Override
    public int isExistGrade(Grade grade) {
        String sql = "select COUNT(*) AS count from UserSelectList where UserID = ? AND CNumber LIKE ?";
        //int result = jdbcTemplate.queryForObject(sql, Integer.class, grade.getMemberId(), grade.getClassNum());
        //return result;
        return 0;
    }

    @Override
    public int getAllScore(String memberId) {
        return 0;
    }

    @Override
    public int getMajorScore(String memberId) {
        return 0;
    }

    @Override
    public void getCredit(String memberId) {

    }

}
