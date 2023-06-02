package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.EnglishLecture;
import graduationProject.graduation_judge.DTO.Lecture.EnglishLectureDTO;
import graduationProject.graduation_judge.domain.Lecture.repository.EnglishLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnglishLectureServiceImpl implements EnglishLectureService{

    @Autowired
    private EnglishLectureRepository englishLectureRepository;

    @Override //영어강의인지 확인
    public boolean isEnglishLecture(String isEnglish) {
        return false;
    }

    @Override //EnglishLecture table에 추가
    public void inputEnglishLecture(EnglishLectureDTO englishLectureDTO) {
        englishLectureRepository.save(englishLectureDTO.toEntity());
    }

    @Override //EnglishLecture (학기, 학수번호)에 해당하는 tuple table에서 제거
    public void deleteEnglishLectureTuple(String termNumber, String classNumber) {
        englishLectureRepository.deleteAllByTermNumAndClassNum(termNumber, classNumber);
    }

    @Override //EnglishLecture table 수정 ? 필요한가 ?
    public void modifyEnglishLectureTuple(EnglishLectureDTO englishLectureDTO) {
        englishLectureRepository.save(englishLectureDTO.toEntity());
    }

    @Override //전체 테이블 삭제
    public void deleteEnglishLectureTable() {
        englishLectureRepository.deleteAll();
    }

}
