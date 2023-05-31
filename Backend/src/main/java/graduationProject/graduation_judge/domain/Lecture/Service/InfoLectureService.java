package graduationProject.graduation_judge.domain.Lecture.Service;

import org.springframework.stereotype.Service;

@Service
public interface InfoLectureService {
    //원래 엑셀 파일 컬럼
    //(번호), (학년/가진급학년), !(교과과정)!, !(교과영역구분)!, !(학수강좌번호)!,
    // !(교과목명)!, !(교원명)!, (수업캠퍼스), (요일/시간), (강의실),
    // !(학점)!, (이론), (실습), !(공학설계)!, (강의계획서),
    // (교과목해설), (강의유형), (강의종류), (혁신교과목구분), (원어강의),
    // (강평결과), (이수구분), (강의미리보기), (개설대학), (개설학과/전공),
    // (집중이수학기구분), (비고), (교과목영문명)

    // 정규화 전 lecture 컬럼
    // infolecture, entirelecture, designlecture, englishlecture에 필요한 것만 담은 JSON정보
    // (학기 TermNumber), (학수번호 ClassNumber), (교수님성함 ProfessorName),
    // (교과목명 LectureNick), (교과과정 Curriculum),
    // (교과영역구분 ClassArea), (학점 ClassCredit),
    // (설계학점 DesignCredit)

    //bsm_수학, bsm_과학, 기본소양 db에 추가...ClassArea repository만들기? 구분이 필요할까 생각하기
    //위와 같이 ClassArea를 구분안하고 그냥 엑셀에서 "교과영역구분"을 input해도 괜찮을지?
}
