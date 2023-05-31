package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DTO.InfoLectureDTO;
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

    //위에서 (강의명), (교과과정), (교과영역구분), (학점), (학수번호)PK 추가

    //InfoLecture table에 추가
    void inputInfoLecture(InfoLectureDTO infoLectureDTO);

    //InfoLecture 학수번호에 해당하는 tuple table에서 제거
    void deleteInfoLectureTuple(String classNumber);

    //InfoLecture table 수정 ? 필요한가 ?
    void modifyInfoLectureTuple(InfoLectureDTO infoLectureDTO);

    //전체 테이블 삭제
    void deleteInfoLectureTable();

    //...
}
