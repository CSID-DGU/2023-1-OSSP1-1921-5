package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.EnglishLecture;
import graduationProject.graduation_judge.DTO.EnglishLectureDTO;
import org.springframework.stereotype.Service;

@Service
public class EnglishLectureServiceImpl implements EnglishLectureService{
    @Override
    public void inputEnglishLecture(EnglishLectureDTO englishLectureDTO) {

    }

    @Override
    public void deleteEnglishLectureTuple(String termNumber, String classNumber) {

    }

    @Override
    public void modifyEnglishLectureTuple(EnglishLectureDTO englishLectureDTO) {

    }

    @Override
    public void deleteEnglishLectureTable() {

    }

    @Override
    public EnglishLecture toEntity(EnglishLectureDTO englishLectureDTO) {
        return null;
    }
}
