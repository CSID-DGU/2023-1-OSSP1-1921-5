package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class LectureServiceImpl implements LectureService{

    @Autowired
    private InfoLectureService infoLectureService;
    @Autowired
    private EntireLectureService entireLectureService;
    @Autowired
    private EnglishLectureService englishLectureService;
    @Autowired
    private DesignLectureService designLectureService;

    @Override
    public void inputLecture(GetLectureInfoIncludeSemesterDTO getLectureDTO) {
        infoLectureService.inputInfoLecture(getLectureDTO);
        entireLectureService.inputEntireLecture(getLectureDTO);
        englishLectureService.inputEnglishLecture(getLectureDTO);
        designLectureService.inputDesignLecture(getLectureDTO);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> a845162b1cd79b1ff7d5af80a528cd74def4235f
