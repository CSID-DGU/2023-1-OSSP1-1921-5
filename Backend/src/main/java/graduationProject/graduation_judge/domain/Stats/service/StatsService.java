package graduationProject.graduation_judge.domain.Stats.service;

import org.springframework.stereotype.Service;

@Service
public interface StatsService {
    // ScoreStat 삭제 (member 단위)
    void deleteStatByMemberId(String memberId);
}
