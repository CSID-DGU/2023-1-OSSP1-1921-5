package graduationProject.graduation_judge.domain.Stats.service;

import graduationProject.graduation_judge.DTO.ScoreStatDTO;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private ScoreStatRepository scoreStatRepository;

    @Override
    public void deleteStatByMemberId(String memberId) {
        scoreStatRepository.deleteAllByMemberId(memberId);
    }

    @Override
    public void insertScoreStat(ScoreStatDTO scoreStatDTO) {
        scoreStatRepository.save(scoreStatDTO.toEntity());
    }
}
