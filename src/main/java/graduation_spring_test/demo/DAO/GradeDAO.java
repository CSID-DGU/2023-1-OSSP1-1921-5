package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Grade.Grade;

public interface GradeDAO {
    void addGrade(Grade grade); // 성적 추가
    void deleteGradeByLec(String cNum); // 과목단위 성적 삭제(동일과목 재수강 시, 기존 성적 제거하고 새로운 성적 insert 필요)
    void deleteGradeByMember(String memberId); // member 성적 삭제
    void getGrade(String cNum); // 특정 과목 성적 조회

    Boolean isExistGrade(Grade grade); // 특정 과목 수강 여부

    void getAllScore(String memberId);// 전체 성적 평점 계산

    void getMajorScore(String memberId);// 전공 성적 평점 계산
    
    void getCredit(String memberId); // 전체 이수 학점 계산
}
