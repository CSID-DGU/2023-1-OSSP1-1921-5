package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.InfoLecture;
import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import graduationProject.graduation_judge.DTO.Lecture.InfoLectureDTO;
import org.springframework.stereotype.Service;

@Service
public interface InfoLectureService {
    // 정규화 전 lecture 컬럼
    // infolecture, entirelecture, designlecture, englishlecture에 필요한 것만 담은 JSON정보
    // (학기 TermNumber), (학수번호 ClassNumber), (교수님성함 ProfessorName),
    // (교과목명 LectureNick), (교과과정 Curriculum)->이수구분으로 할듯,
    // (교과영역구분 ClassArea), (학점 ClassCredit),
    // (설계학점 DesignCredit)

    //Front에서 받아오는 JSON
    //"year": "2018", "semester":"2", "lectureDataList":[{"ProfessorName":"김진선",
    //"LectureNick":"행복학의인문학적문제들","Curriculum":"핵심교양","ClassArea":"핵심",
    //"ClassCredit":"3","IsEnglish":0}, ....]

    //위에서 (강의명), (교과과정), (교과영역구분), (학점), (학수번호)PK 추가

    //InfoLecture table에 추가
    void inputInfoLecture(GetLectureInfoIncludeSemesterDTO getLectureDTO);

    //InfoLecture 학수번호에 해당하는 tuple table에서 제거
    void deleteInfoLectureTuple(String classNumber);

    //InfoLecture table 수정 ? 필요한가 ?
    void modifyInfoLectureTuple(InfoLectureDTO infoLectureDTO);

    //전체 테이블 삭제
    void deleteInfoLectureTable();

    //...
}
