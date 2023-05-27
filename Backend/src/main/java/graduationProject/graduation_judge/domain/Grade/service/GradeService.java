package graduationProject.graduation_judge.domain.Grade.service;

import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DTO.GradeDTO;
import org.springframework.stereotype.Service;

@Service
public interface GradeService {
    //성적 입력(동일과목 재수강 시, 기존 성적 제거하고 새로운 성적 insert 필요)
    void inputGrade(GradeDTO grade);

    //member 성적 존재하는지 여부
    boolean isExistGrade(String memberId);

    //특정 과목 성적 조회
    String getGradeByLec(String cNum);

    //성적 삭제 (member 단위)
    void deleteGradeByMember(String memberId);

    //특정 member의 전체 성적 평점 계산
    float getAllScoreByMember(String memberID);

    //특정 member의 전공 성적 평점 계산
    float getMajorScoreByMember(String memberId);

    //전체 member의 전체 성적 평점 계산
    float getEntireAllScore();

    //전체 member의 전공 성적 평점 계산
    float getEntireMajorScore();

    // GradeDTO to Entity
    UserSelectList toEntity(GradeDTO gradeDTO);
}
