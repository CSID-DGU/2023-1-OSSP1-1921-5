package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.EntireLecture;
import graduationProject.graduation_judge.DTO.Lecture.EntireLectureDTO;
import org.springframework.stereotype.Service;

@Service
public class EntireLectureServiceImpl implements EntireLectureService{


    @Override
    public void inputEntireLecture(EntireLectureDTO entireLectureDTO) {

    }

    @Override
    public void deleteEntireLectureTuple(String classNumber, String termNumber) {

    }

    @Override
    public void modifyEntireLectureTuple(EntireLectureDTO entireLectureDTO) {

    }

    @Override
    public void deleteEntireLectureTable() {

    }

    @Override
    public EntireLecture toEntity(EntireLectureDTO entireLectureDTO) {
        return null;
    }
}
