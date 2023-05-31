package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DTO.EntireLectureDTO;
import graduationProject.graduation_judge.DTO.InfoLectureDTO;
import org.springframework.stereotype.Service;

@Service
public interface EntireLectureService {
    //infolecture에 넣은 후에 entirelecture에 넣어야함
    //(학기), (학수번호), (교수님성함) 넣기

    //EntireLecture table에 추가
    void inputEntireLecture(EntireLectureDTO entireLectureDTO);

    //InfoLecture 학수번호에 해당하는 tuple table에서 제거
    void deleteEntireLectureTuple(String classNumber, String termNumber);

    //InfoLecture table 수정 ? 필요한가 ?
    void modifyEntireLectureTuple(EntireLectureDTO entireLectureDTO);

    //전체 테이블 삭제
    void deleteEntireLectureTable();
}
