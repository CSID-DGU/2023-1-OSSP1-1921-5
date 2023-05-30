package graduationProject.graduation_judge.domain.Stats.repository;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.domain.Lecture.LectureId;
import graduationProject.graduation_judge.domain.Stats.ScoreStatId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreStatRepository extends JpaRepository<ScoreStat, ScoreStatId> {
    ScoreStatId findByMemberId(String memberId);
    void deleteAllByMemberId(String memberId);

}
