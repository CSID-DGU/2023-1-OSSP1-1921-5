package graduationProject.graduation_judge.domain.Lecture.repository;

import graduationProject.graduation_judge.DAO.InfoLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoLectureRepository extends JpaRepository<InfoLecture, String> {
    void deleteAllByClassNumber(String ClassNumber);
}
