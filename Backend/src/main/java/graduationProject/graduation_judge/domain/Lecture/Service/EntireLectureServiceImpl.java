package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.EntireLecture;
import graduationProject.graduation_judge.DTO.Lecture.EntireLectureDTO;
import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoListDTO;
import graduationProject.graduation_judge.domain.Lecture.repository.EntireLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntireLectureServiceImpl implements EntireLectureService{

    @Autowired
    private EntireLectureRepository entireLectureRepository;

    //infolecture에 넣은 후에 entirelecture에 넣어야함
    //(학기), (학수번호), (교수님성함) 넣기
    @Override //EntireLecture에 강의 하나 정보 넣기
    public void inputEntireLecture(GetLectureInfoIncludeSemesterDTO getLectureDTO) {
        List<GetLectureInfoListDTO> lectureInfoList = getLectureDTO.getLectureDataList();

        String termNumber = getLectureDTO.getYear()+"_"+ getLectureDTO.getSemester();
        for (GetLectureInfoListDTO lectureData : lectureInfoList) {
            EntireLectureDTO entireLectureDTO = new EntireLectureDTO(
                    termNumber,
                    lectureData.getClassNumber(),
                    lectureData.getProfessorName()
            );
            entireLectureRepository.save(entireLectureDTO.toEntity());
        }
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
