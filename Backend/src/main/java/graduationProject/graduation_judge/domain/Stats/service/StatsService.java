package graduationProject.graduation_judge.domain.Stats.service;

import graduationProject.graduation_judge.DTO.ScoreStatDTO;
import org.springframework.stereotype.Service;

@Service
public interface StatsService {
    // ScoreStat 삭제 (member 단위)
    void deleteStatByMemberId(String memberId);

    // 입력
    void insertScoreStat(ScoreStatDTO scoreStatDTO);
}
