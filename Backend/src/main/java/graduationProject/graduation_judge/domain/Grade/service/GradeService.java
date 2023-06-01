package graduationProject.graduation_judge.domain.Grade.service;

import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DTO.GradeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService {
    //성적 입력(동일과목 재수강 시, 기존 성적 제거하고 새로운 성적 insert 필요)
    void inputGrade(GradeDTO grade);

    //member 성적 존재하는지 여부
    boolean isExistGrade(String memberId);

    //UserSelectList 조회
    //List<UserSelectList> getUserSelecListbyMemberId(String memberId);

    //특정 과목 성적 조회
    String getGradeByLec(String cNum);

    //성적 삭제 (member 단위)
    void deleteGradeByMember(String memberId);

    //특정 member의 전체 성적 평점 계산
    float getAllScoreByMember(String memberID);

    //특정 member의 전공 성적 평점 계산
    float getMajorScoreByMember(String memberId);

    //특정 member의 전체 성적 평점 계산
    float getEntireAllScore(String memberId);

    //전체 member의 전공 성적 평점 계산
    float getEntireMajorScore();

    //특정 member의 총 이수학점을 계산
    int getTotalClassCredit(String memberId);

    //특정 member의 총 이수과목 수를 계산
    int getCompletedCourseCount(String memberId);
}
