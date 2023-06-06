package graduationProject.graduation_judge.domain.Grade.service;

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

    //특정 member의 성적 평점 계산
    float getClassScore(String memberId, String termNum, String option);

    //특정 member의 이수학점을 계산 (전체 or 학기마다)
    int getClassCredit(String memberId, String termNum, String option);

    //특정 member의 학기 리스트 반환
    List<String> getTermList(String memberId);

    //특정 member의 총 이수과목 수를 계산
    //int getCompletedCourseCount(String memberId);
}
