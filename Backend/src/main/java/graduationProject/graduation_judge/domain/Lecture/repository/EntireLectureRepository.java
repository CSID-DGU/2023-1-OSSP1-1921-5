package graduationProject.graduation_judge.domain.Lecture.repository;

import graduationProject.graduation_judge.DAO.EntireLecture;
import graduationProject.graduation_judge.DAO.InfoLecture;
import graduationProject.graduation_judge.DAO.identifier.EntireLecturePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntireLectureRepository extends JpaRepository<EntireLecture, EntireLecturePK> {
    //강의정보를 받아서 저장
}
