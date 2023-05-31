package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.DesignLecture;
import graduationProject.graduation_judge.DTO.Lecture.DesignLectureDTO;
import org.springframework.stereotype.Service;

@Service
public interface DesignLectureService {
    //infolecture에 넣은 후에 entirelecture에 넣은 후에 Designlecture에 넣어야함
    //(학기), (학수번호), (설계학점) 넣기

    //DesignLecture table에 추가
    void inputDesignLecture(DesignLectureDTO designLectureDTO);

    //DesignLecture (학기, 학수번호)에 해당하는 tuple table에서 제거
    void deleteDesignLectureTuple(String termNumber, String classNumber);

    //DesignLecture table 수정 ? 필요한가 ?
    void modifyDesignLectureTuple(DesignLectureDTO designLectureDTO);

    //전체 테이블 삭제
    void deleteDesignLectureTable();

    //DesignLectureDTO to Entity
    DesignLecture toEntity(DesignLectureDTO designLectureDTO);
}
