package graduationProject.graduation_judge.domain.Graduation.repository;

import graduationProject.graduation_judge.DAO.GraduationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduationRepository extends JpaRepository<GraduationDAO, Long> {

}
