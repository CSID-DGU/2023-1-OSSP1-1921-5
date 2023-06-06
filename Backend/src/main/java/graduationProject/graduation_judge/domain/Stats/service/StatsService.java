package graduationProject.graduation_judge.domain.Stats.service;

import graduationProject.graduation_judge.DTO.ScoreStatDTO;
import org.springframework.stereotype.Service;

@Service
public interface StatsService {

    // 입력
    void insertScoreStat(ScoreStatDTO scoreStatDTO);

    // member 별로 조회
    void getMemberScoreStats(String memberId);

    // semester 별로 조회
    void getSemScoreStats(int semester);
}
