package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Grade.Grade;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

@Repository
public class GradeDAOImpl implements GradeDAO{

    private final EntityManager entityManager;

    @Autowired
    public GradeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addGrade(Grade grade) {
        entityManager.persist(grade);
    }

    @Override
    public void deleteGrade(Grade grade) {
        entityManager.remove(grade);
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
    public float getSumOfAllScore(String memberId) {
        return 0;
    }

    @Override
    public float getSumOfMajorScore(String memberId) {
        return 0;
    }

    @Override
    public int getCredit(String memberId) {
        return 0;
    }

}
