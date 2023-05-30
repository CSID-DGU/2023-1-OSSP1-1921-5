package graduationProject.graduation_judge.domain.Stats.repository;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DAO.identifier.ScoreStatPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreStatRepository extends JpaRepository<ScoreStat, ScoreStatPK> {
    ScoreStatPK findByMemberId(String memberId);
    void deleteAllByMemberId(String memberId);

}
