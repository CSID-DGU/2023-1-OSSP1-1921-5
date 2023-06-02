package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.EntireLecture;
import graduationProject.graduation_judge.DTO.Lecture.EntireLectureDTO;
import graduationProject.graduation_judge.domain.Lecture.repository.EntireLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntireLectureServiceImpl implements EntireLectureService{

    @Autowired
    private EntireLectureRepository entireLectureRepository;

    @Override //EntireLecture에 강의 하나 정보 넣기
    public void inputEntireLecture(EntireLectureDTO entireLectureDTO) {
        entireLectureRepository.save(entireLectureDTO.toEntity());
    }

    @Override //EntireLecture에서 강의 하나 삭제
    public void deleteEntireLectureTuple(String termNumber, String classNumber) {
        entireLectureRepository.deleteAllByTermNumberAndClassNumber(termNumber, classNumber);
    }

    @Override //EntireLecture에서 강의 정보 하나 수정
    public void modifyEntireLectureTuple(EntireLectureDTO entireLectureDTO) {
        entireLectureRepository.save(entireLectureDTO.toEntity());
    }

    @Override //EntireLecture Table 전체 삭제
    public void deleteEntireLectureTable() {
        entireLectureRepository.deleteAll();
    }

}
