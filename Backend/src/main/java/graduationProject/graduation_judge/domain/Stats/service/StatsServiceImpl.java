package graduationProject.graduation_judge.domain.Stats.service;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DTO.ScoreStatDTO;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private ScoreStatRepository scoreStatRepository;


    @Override
    public void insertScoreStat(ScoreStatDTO scoreStatDTO) {
        scoreStatRepository.save(scoreStatDTO.toEntity());
    }

    @Override
    public void getMemberScoreStats(String memberId) {
        // member 별로 조회
        List<ScoreStat> scoreStatList = scoreStatRepository.findAllByMemberId(memberId);

    }

    @Override
    public void getSemScoreStats(int semester) {
        // semester 별로 조회
    }
}
