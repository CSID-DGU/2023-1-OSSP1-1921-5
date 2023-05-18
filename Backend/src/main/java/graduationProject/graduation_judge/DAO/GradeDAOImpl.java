package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.domain.Grade.Grade;
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
    public void deleteGrade(Grade grade) {

    }

    @Override
    public String getGrade(String cNum) {
        return null;
    }

    @Override
    public int isExistGrade(Grade grade) {
        String sql = "select COUNT(*) AS count from UserSelectList where UserID = ? AND CNumber LIKE ?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, grade.getMemberId(), grade.getClassNum());
        return result;
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
