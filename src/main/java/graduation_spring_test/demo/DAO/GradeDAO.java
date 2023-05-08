package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Grade.Grade;

public interface GradeDAO {
    void addGrade(Grade grade); // 성적 추가
    void deleteGrade(Grade grade); // 성적 삭제
    void getGrade(String cNum); // 성적 조회
}
