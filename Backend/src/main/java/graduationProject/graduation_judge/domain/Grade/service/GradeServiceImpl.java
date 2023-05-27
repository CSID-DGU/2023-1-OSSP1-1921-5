package graduationProject.graduation_judge.domain.Grade.service;

import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DTO.GradeDTO;
import graduationProject.graduation_judge.domain.Grade.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeRepository gradeRepository;


    @Override
    public void inputGrade(GradeDTO grade) {
        // 성적 입력
        // 이미 해당 data가 존재하면 삭제후 삽입
//        if(gradeRepository.isExistGrade(grade)>0) {
//            gradeRepository.deleteGrade(grade);
//        }
        gradeRepository.save(toEntity(grade));
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
    public void deleteGradeByMember(String memberId) {
        //성적 삭제 (member 단위)
        //gradeDao.deleteGrade(memberId);
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
    public float getEntireAllScore() {
        //전체 member의 전체 성적 평점 계산
        return 0;
    }

    @Override
    public float getEntireMajorScore() {
        //전체 member의 전공 성적 평점 계산
        return 0;
    }

    @Override
    public UserSelectList toEntity(GradeDTO gradeDTO) {
        // GradeDTO to Entity
        return new UserSelectList(gradeDTO.getMemberId(), gradeDTO.getTermNum(), gradeDTO.getClassNum(), gradeDTO.getScore());
    }
}
