package graduationProject.graduation_judge.domain.Stats.service;

import graduationProject.graduation_judge.DTO.GraphInfo;
import graduationProject.graduation_judge.DTO.ScoreStatDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatsService {

    // 입력
    void insertScoreStat(ScoreStatDTO scoreStatDTO);

    // semester 별 평점 GraphInfo 구하기(전체, 전공)
    GraphInfo.GraphData getGradeGraphInfo(int semester, String memberId, String typeId);
    
    // semester 별 이수학점 GraphInfo 구하기
    GraphInfo.GraphData getCreditGraphInfo(int semester, String memberId, List<GraphInfo.GraphData> creditData);
}
