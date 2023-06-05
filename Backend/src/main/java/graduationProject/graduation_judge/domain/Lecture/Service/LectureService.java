package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import org.springframework.stereotype.Service;

@Service
public interface LectureService {
    void inputLecture(GetLectureInfoIncludeSemesterDTO getLectureDTO); //db에 강의저장
}
