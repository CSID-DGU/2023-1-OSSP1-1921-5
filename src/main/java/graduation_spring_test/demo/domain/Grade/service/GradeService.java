package graduation_spring_test.demo.domain.Grade.service;

import graduation_spring_test.demo.domain.Grade.Grade;

public interface GradeService {

    //성적 입력(동일과목 재수강 시, 기존 성적 제거하고 새로운 성적 insert 필요)
    void inputGrade(Grade grade);

    //특정 과목 성적 조회
    String getGradeByLec(String cNum);

    //성적 삭제 (member 단위)
    void deleteGradeByMember(String memberId);

    //특정 member의 전체 성적 평점 계산
    int AllScoreByMember(String memberID);

    //특정 member의 전공 성적 평점 계산
    int MajorScoreByMember(String memberId);

    //전체 member의 전체 성적 평점 계산
    
    //전체 member의 전공 성적 평점 계산
}
