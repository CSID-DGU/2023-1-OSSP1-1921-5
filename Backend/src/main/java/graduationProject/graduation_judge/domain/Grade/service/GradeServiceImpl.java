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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public float getAllScoreByMember(String memberId) {
        //특정 member의 전체 성적 평점 계산
//        float sum = gradeDao.getSumOfAllScore(memberId);
//        int num = gradeDao.getCredit(memberId);
//        return sum/num;
        return 0;
    }

    @Override
    public float getMajorScoreByMember(String memberId) {
        //특정 member의 전공 성적 평점 계산
//        float sum = gradeDao.getSumOfMajorScore(memberId);
//        int num = gradeDao.getCredit(memberId);
//        return sum/num;
        return 0;
    }

    @Override
    public float getEntireAllScore(String memberId) {
        //특정 member의 전체 성적 평점 계산
        List<UserSelectList> userSelectLists = gradeRepository.findAllByMemberId(memberId);

        float totalScore = 0;
        int totalCredit = 0;
        int credit;
        String grade;
        for (UserSelectList userSelectList : userSelectLists){
            grade = userSelectList.getScore();
            credit = userSelectList.getUs_entireLecture().getInfoLectures().getClassCredit();
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
    public int getTotalClassCredit(String memberId, Optional<String> termNum) {
        //특정 member의 총 이수학점을 계산
        List<UserSelectList> userSelectLists = new ArrayList<UserSelectList>();
        int totalClassCredit = 0;

        if (termNum.isPresent()){ // 특정 이수 학기의 총 이수학점
            userSelectLists = gradeRepository.findAllByMemberIdAndTermNum(memberId, termNum.get());
        }
        else{ // 전체 이수 학기의 총 이수학점
            userSelectLists = gradeRepository.findAllByMemberId(memberId);
        }

        for(UserSelectList selectList: userSelectLists){
            InfoLecture infoLecture = selectList.getUs_entireLecture().getInfoLectures();
            totalClassCredit += infoLecture.getClassCredit();
        }

        return totalClassCredit;
    }

    @Override
    public List<String> getTermList(String memberId) {
        //특정 member의 학기 리스트 반환
        List<String> termList = gradeRepository.findDistinctTermNumByMemberId(memberId);
        return termList;
    }

//    @Override
//    public int getCompletedCourseCount(String memberId) {
//        //특정 member의 총 이수과목 수를 계산
//        return gradeRepository.countAllByMemberId(memberId);
//    }

}
