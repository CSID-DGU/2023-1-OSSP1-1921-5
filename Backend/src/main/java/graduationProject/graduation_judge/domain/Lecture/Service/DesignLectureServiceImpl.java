package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.DesignLecture;
import graduationProject.graduation_judge.DTO.DesignLectureDTO;
import org.springframework.stereotype.Service;

@Service
public class DesignLectureServiceImpl implements DesignLectureService{
    @Override
    public void inputDesignLecture(DesignLectureDTO designLectureDTO) {

    }

    @Override
    public void deleteDesignLectureTuple(String termNumber, String classNumber) {

    }

    @Override
    public void modifyDesignLectureTuple(DesignLectureDTO designLectureDTO) {

    }

    @Override
    public void deleteDesignLectureTable() {

    }

    @Override
    public DesignLecture toEntity(DesignLectureDTO designLectureDTO) {
        return null;
    }
}
