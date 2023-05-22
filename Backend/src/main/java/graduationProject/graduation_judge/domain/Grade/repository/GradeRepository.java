package graduationProject.graduation_judge.domain.Grade.repository;

import graduationProject.graduation_judge.DAO.Grade;
import graduationProject.graduation_judge.domain.Grade.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradeId> {
}
