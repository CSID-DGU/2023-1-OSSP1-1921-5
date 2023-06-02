package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.EnglishLecture;
import graduationProject.graduation_judge.DTO.Lecture.EnglishLectureDTO;
import org.springframework.stereotype.Service;

@Service
public interface EnglishLectureService {
    //infolecture에 넣은 후에 entirelecture에 넣은 후에 Designlecture에 넣어야함
    //IsEnglish == 1 이면 (학기), (학수번호)넣기

    //영어강의인지 확인
    boolean isEnglishLecture(String isEnglish);

    //EnglishLecture table에 추가
    void inputEnglishLecture(EnglishLectureDTO englishLectureDTO);

    //EnglishLecture (학기, 학수번호)에 해당하는 tuple table에서 제거
    void deleteEnglishLectureTuple(String termNumber, String classNumber);

    //EnglishLecture table 수정 ? 필요한가 ?
    void modifyEnglishLectureTuple(EnglishLectureDTO englishLectureDTO);

    //전체 테이블 삭제
    void deleteEnglishLectureTable();

}
