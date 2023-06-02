package graduationProject.graduation_judge.domain.Lecture.repository;

import graduationProject.graduation_judge.DAO.DesignLecture;
import graduationProject.graduation_judge.DAO.identifier.DesignLecturePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignLectureRepository extends JpaRepository<DesignLecture, DesignLecturePK> {
    void deleteAllByTermNumAndClassNum(String termNumber, String classNumber);
}
