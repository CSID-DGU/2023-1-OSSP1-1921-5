package graduationProject.graduation_judge;

import graduationProject.graduation_judge.DAO.Grade;
import graduationProject.graduation_judge.domain.Grade.repository.GradeRepository;
import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GradeTest {

//    @Configuration
//    static class SpringConfig{
//        private final EntityManager em;
//        public SpringConfig(EntityManager em){
//            this.em=em;
//        }
//
//    }

    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    GradeService gradeService;

    @Test
    void inputGrade(){
        Grade grade = new Grade("11@naver.com", "2021_1", "CSE2014-02", "A+");
        this.gradeRepository.save(grade);

    }
}
