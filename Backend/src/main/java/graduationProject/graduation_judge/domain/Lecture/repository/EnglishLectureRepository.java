package graduationProject.graduation_judge.domain.Lecture.repository;

import graduationProject.graduation_judge.DAO.EnglishLecture;
import graduationProject.graduation_judge.DAO.identifier.EnglishLecturePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnglishLectureRepository extends JpaRepository<EnglishLecture, EnglishLecturePK> {
    void deleteAllByTermNumAndClassNum(String termNumber, String classNumber);
}
