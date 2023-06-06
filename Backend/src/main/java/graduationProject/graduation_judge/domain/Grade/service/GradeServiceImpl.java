package graduationProject.graduation_judge.domain.Grade.service;

import graduationProject.graduation_judge.DAO.InfoLecture;
import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DAO.identifier.ScoreStatPK;
import graduationProject.graduation_judge.DTO.GradeDTO;
import graduationProject.graduation_judge.domain.Grade.repository.GradeRepository;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private ScoreStatRepository scoreStatRepository;


    @Override
    public void inputGrade(GradeDTO grade) {
        // 성적 입력
        gradeRepository.save(grade.toEntity());
    }

    @Override
    public boolean isExistGrade(String memberId) {
        //member 성적 존재하는지 여부
        return gradeRepository.existsByMemberId(memberId);
    }

    @Override
    public String getGradeByLec(String cNum) {
        //특정 과목 성적 조회
        //return gradeRepository.getGrade(cNum);
        return null;
    }

    @Override
    @Transactional
    public void deleteGradeByMember(String memberId) {
        //성적 삭제 (member 단위)
        gradeRepository.deleteAllByMemberId(memberId);
    }

    @Override
    public float getClassScore(String memberId, String termNum, String option) {
        // 성적 평점 계산
        List<UserSelectList> userSelectLists;
        if (termNum == null){ // 전체 이수 학기 기준
            userSelectLists = gradeRepository.findAllByMemberId(memberId);
        }
        else{ // 특정 이수 학기 기준
            userSelectLists = gradeRepository.findAllByMemberIdAndTermNum(memberId, termNum);
        }

        float totalScore = 0;
        int totalCredit = 0;
        int credit;
        String grade;
        for (UserSelectList userSelectList : userSelectLists){
            if(option != null){ //"전공" 성적 평점 계산
                if(!option.equals(userSelectList.getEntireLecture().getInfoLecture().getCurriculum())){
                    continue;
                }
            }
            grade = userSelectList.getScore();
            credit = userSelectList.getEntireLecture().getInfoLecture().getClassCredit();
            totalCredit+=credit;
            if (grade.equals("A+")) {
                totalScore += 4.5f * credit;
            } else if (grade.equals("A0")) {
                totalScore += 4.0f * credit;
            } else if (grade.equals("B+")) {
                totalScore += 3.5f * credit;
            } else if (grade.equals("B0")) {
                totalScore += 3.0f * credit;
            } else if (grade.equals("C+")) {
                totalScore += 2.5f * credit;
            } else if (grade.equals("C0")) {
                totalScore += 2.0f * credit;
            } else if (grade.equals("D+")) {
                totalScore += 1.5f * credit;
            } else if (grade.equals("D0")) {
                totalScore += 1.0f * credit;
            } else if (grade.equals("P")) {
                totalCredit -= credit;
            } else if (grade.equals("F")) {

            }
        }

        totalScore = totalScore/totalCredit;

        return totalScore;
    }

    @Override
    public float getEntireMajorScore() {
        //전체 member의 전공 성적 평점 계산
        return 0;
    }

    @Override
    public int getClassCredit(String memberId, String termNum, String option) {
        //특정 member의 총 이수학점을 계산
        List<UserSelectList> userSelectLists;
        int totalClassCredit = 0;

        if (termNum == null){ // 전체 이수 학기의 총 이수학점
            userSelectLists = gradeRepository.findAllByMemberId(memberId);
        }
        else{ // 특정 이수 학기의 총 이수학점
            userSelectLists = gradeRepository.findAllByMemberIdAndTermNum(memberId, termNum);
        }

        for(UserSelectList selectList: userSelectLists){
            if(option != null){ //"전공" 이수학점 계산
                if(!option.equals(selectList.getEntireLecture().getInfoLecture().getCurriculum())){
                    continue;
                }
            }
            InfoLecture infoLecture = selectList.getEntireLecture().getInfoLecture();
            totalClassCredit += infoLecture.getClassCredit();
        }

        return totalClassCredit;
    }

    @Override
    public List<String> getTermList(String memberId) {
        //특정 member의 학기 리스트 반환
        List<UserSelectList> UserSelectLists = gradeRepository.findAllByMemberId(memberId);
        Set<String> uniqueTermNums = new HashSet<>();
        for (UserSelectList userSelectList : UserSelectLists) {
            uniqueTermNums.add(userSelectList.getTermNum());
        }
        List<String> uniqueTermNumList = new ArrayList<>(uniqueTermNums);

        return uniqueTermNumList;
    }

//    @Override
//    public int getCompletedCourseCount(String memberId) {
//        //특정 member의 총 이수과목 수를 계산
//        return gradeRepository.countAllByMemberId(memberId);
//    }

}
