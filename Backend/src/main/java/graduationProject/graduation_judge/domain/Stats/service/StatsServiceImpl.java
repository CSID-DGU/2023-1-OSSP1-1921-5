package graduationProject.graduation_judge.domain.Stats.service;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DTO.Stats.GraphInfo;
import graduationProject.graduation_judge.DTO.Stats.ScoreStatDTO;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public GraphInfo.GraphData getGradeGraphInfo(int semester, String memberId, String typeId) {
        // semester 별 전공 or 전체 GraphInfo 구하기
        float myData = 0;
        float avgData = 0;
        float allGradeSum = 0;
        int numMember; // 총 멤버 수

        if (typeId == "전체" || typeId == "전공") {
            // 먼저 member의 평점
            ScoreStat myScoreStat = scoreStatRepository.findBySemesterAndMemberIdAndTypeId(semester, memberId, typeId);
            if (myScoreStat != null)
                myData = myScoreStat.getGrade();

            // 모든 member의 평점
            List<ScoreStat> allScoreStat = scoreStatRepository.findAllBySemesterAndTypeId(semester, typeId);
            numMember = allScoreStat.size();
            if (numMember != 0) {
                for (ScoreStat scoreStat : allScoreStat) {
                    allGradeSum += scoreStat.getGrade();
                }
                avgData = allGradeSum / numMember;
            }

            return new GraphInfo.GraphData(semester, myData, avgData);
        } else {
            return null;
        }
    }

    @Override
    public GraphInfo.GraphData getCreditGraphInfo(int semester, String memberId) {
        // semester 별 이수학점 GraphInfo 구하기
        int myCredit = 0;
        int avgCredit = 0;
        int sumOfAllCredit = 0;
        int numMember; // 총 멤버 수

        for (int sem = 1; sem <= semester; sem++) {
            // member의 누적 이수학점 구하기
            ScoreStat myScoreStat = scoreStatRepository.findBySemesterAndMemberIdAndTypeId(sem, memberId, "전체");
            if (myScoreStat != null) {
                myCredit += myScoreStat.getCredit();
            }
            else{ // member가 semester만큼 학기를 이수하지 않음
                myCredit = 0;
                break;
            }
        }

        // 모든 member의 누적 이수학점 구하기
        List<ScoreStat> allScoreStat = scoreStatRepository.findAllBySemesterAndTypeId(semester, "전체");
        numMember = allScoreStat.size();
        if (numMember != 0) {
            for (ScoreStat scoreStat : allScoreStat) {
                for (int sem = 1; sem <= semester; sem++) {
                    // semester학기까지 이수한 member들의 누적 이수학점 구하기
                    ScoreStat myScoreStat = scoreStatRepository.findBySemesterAndMemberIdAndTypeId(sem, scoreStat.getMemberId(), "전체");
                    if (myScoreStat != null) {
                        sumOfAllCredit += myScoreStat.getCredit();
                    }
                }
            }
            avgCredit = sumOfAllCredit / numMember;
        }
        return new GraphInfo.GraphData(semester, myCredit, avgCredit);
    }
}
