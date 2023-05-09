package graduation_spring_test.demo.domain.Grade.service;

import graduation_spring_test.demo.domain.Grade.Grade;

public interface GradeService {

    //성적 입력
    void inputGrade(Grade grade);

    //특정 과목 성적 조회
    String getGradeByLec(String cNum);

    //성적 삭제 (과목 단위). when: 동일과목 재수강 시, 기존 성적 제거하고 새로운 성적 insert 해야함
    void deleteGradeByLec(String cNum);

    //성적 삭제 (member 단위). when: 회원 탈퇴 시
    void deleteGradeByMember(String memberId);

    //전체 성적 평점 계산

    //전공 성적

    //성적 추가? 업데이트? -> 하나의 수강 과목 성적이 변경될 일이 없지 않나?
    //특정 과목 수강 여부 조회???
}
