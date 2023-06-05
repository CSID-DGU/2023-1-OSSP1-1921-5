package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.InfoLecture;
import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoListDTO;
import graduationProject.graduation_judge.DTO.Lecture.InfoLectureDTO;
import graduationProject.graduation_judge.domain.Lecture.repository.InfoLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoLectureServiceImpl implements InfoLectureService{

    @Autowired
    private InfoLectureRepository infoLectureRepository;

    @Override //InfoLecture Table에 강의 하나 input
    public void inputInfoLecture(GetLectureInfoIncludeSemesterDTO getLectureDTO) {
        List<GetLectureInfoListDTO> lectureInfoList = getLectureDTO.getLectureDataList();

        for (GetLectureInfoListDTO lectureData : lectureInfoList){
            InfoLectureDTO infoLectureDTO = new InfoLectureDTO(
                    lectureData.getLectureNick(),
                    lectureData.getCurriculum(),
                    lectureData.getClassArea(),
                    Integer.parseInt(lectureData.getClassCredit()),
                    lectureData.getClassNumber()
            );//(강의명), (교과과정), (교과영역구분), (학점), (학수번호)PK
            infoLectureRepository.save(infoLectureDTO.toEntity());
        }
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
