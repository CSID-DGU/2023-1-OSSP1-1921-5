package graduationProject.graduation_judge.domain.Lecture.repository;

import graduationProject.graduation_judge.DAO.EntireLecture;
import graduationProject.graduation_judge.DAO.InfoLecture;
import graduationProject.graduation_judge.domain.Lecture.LectureId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoLectureRepository extends JpaRepository<InfoLecture, String> {

}
