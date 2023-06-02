package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.InfoLecture;
import graduationProject.graduation_judge.DTO.Lecture.InfoLectureDTO;
import graduationProject.graduation_judge.domain.Lecture.repository.InfoLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoLectureServiceImpl implements InfoLectureService{

    @Autowired
    private InfoLectureRepository infoLectureRepository;

    @Override //InfoLecture Table에 강의 하나 input
    public void inputInfoLecture(InfoLectureDTO infoLectureDTO) {
        infoLectureRepository.save(infoLectureDTO.toEntity());
    }

    @Override //classNumber에 해당하는 Tuple(강의정보)만 삭제
    public void deleteInfoLectureTuple(String classNumber) {
        infoLectureRepository.deleteAllByClassNumber(classNumber);
    }

    @Override //강의 정보 수정 - 쓰려나?
    public void modifyInfoLectureTuple(InfoLectureDTO infoLectureDTO) {
        infoLectureRepository.save(infoLectureDTO.toEntity());
    }

    @Override //InfoLecture 전체 Table 삭제
    public void deleteInfoLectureTable() {
        infoLectureRepository.deleteAll();
    }

}
