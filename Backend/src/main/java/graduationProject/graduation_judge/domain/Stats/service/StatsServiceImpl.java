package graduationProject.graduation_judge.domain.Stats.service;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DTO.GraphInfo;
import graduationProject.graduation_judge.DTO.ScoreStatDTO;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private ScoreStatRepository scoreStatRepository;


    @Override
    public void insertScoreStat(ScoreStatDTO scoreStatDTO) {
        scoreStatRepository.save(scoreStatDTO.toEntity());
    }

    @Override
    public List<ScoreStatDTO> getMemberScoreStats(String memberId) {
        // member 별로 조회
        List<ScoreStat> scoreStatList = scoreStatRepository.findAllByMemberId(memberId);
        List<ScoreStatDTO> scoreStatDTOList = new ArrayList<>();
        for (ScoreStat scoreStat : scoreStatList) {
            scoreStatDTOList.add(new ScoreStatDTO(scoreStat.getMemberId(), scoreStat.getSemester(), scoreStat.getTypeId(), scoreStat.getGrade(), scoreStat.getCredit()));

        }
        return scoreStatDTOList;
    }

    @Override
    public GraphInfo.GraphData getGradeGraphInfo(int semester, String memberId, String typeId) {
        // semester 별 전공 or 전체 GraphInfo 구하기
        float myData = 0;
        float avgData = 0;
        float allGradeSum = 0;

        if (typeId == "전체" || typeId == "전공") {
            // 먼저 member의 평점
            ScoreStat myScoreStat = scoreStatRepository.findBySemesterAndMemberIdAndTypeId(semester, memberId, typeId);
            if (myScoreStat != null)
                myData = myScoreStat.getGrade();

            // 모든 member의 평점
            List<ScoreStat> allScoreStat = scoreStatRepository.findAllBySemesterAndTypeId(semester, typeId);
            if (allScoreStat != null) {
                for (ScoreStat scoreStat : allScoreStat) {
                    allGradeSum += scoreStat.getGrade();
                }
                if (allScoreStat.size() != 0)
                    avgData = allGradeSum / allScoreStat.size();
            }

            return new GraphInfo.GraphData(semester, myData, avgData);
        } else {
            return null;
        }
    }

    @Override
    public GraphInfo.GraphData getCreditGraphInfo(int semester, String memberId, List<GraphInfo.GraphData> creditData) {
        // semester 별 이수학점 GraphInfo 구하기
        int myCredit = 0;
        int avgCredit = 0;
        int sumOfAllCredit = 0;
        int numMember = 0;

        for (int sem = 1; sem <= semester; sem++) {
            // member의 누적 이수학점 구하기
            ScoreStat myScoreStat = scoreStatRepository.findBySemesterAndMemberIdAndTypeId(sem, memberId, "전체");
            if (myScoreStat != null) {
                myCredit += myScoreStat.getCredit();
            }
        }

        // 모든 member의 누적 이수학점 구하기
        List<ScoreStat> allScoreStat = scoreStatRepository.findAllBySemesterAndTypeId(semester, "전체");

        if (allScoreStat != null) {
            for (ScoreStat scoreStat : allScoreStat) {
                for (int sem = 1; sem <= semester; sem++) {
                    // semester학기까지 이수한 member들의 누적 이수학점 구하기
                    ScoreStat myScoreStat = scoreStatRepository.findBySemesterAndMemberIdAndTypeId(sem, scoreStat.getMemberId(), "전체");
                    if (myScoreStat != null) {
                        sumOfAllCredit += myScoreStat.getCredit();
                    }
                }
            }
            if (allScoreStat.size() != 0)
                avgCredit = sumOfAllCredit / allScoreStat.size();
        }
        return new GraphInfo.GraphData(semester, myCredit, avgCredit);
    }
}
