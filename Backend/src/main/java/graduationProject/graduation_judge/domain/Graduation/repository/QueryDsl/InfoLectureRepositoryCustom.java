package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import graduationProject.graduation_judge.DTO.Lecture.InfoLectureDTO;

import java.util.List;

public interface InfoLectureRepositoryCustom {
    List<String> getUserSelectLectureNicknameList(String user_id);

    List<InfoLectureDTO> getUserSelectLectureInfoList(String user_id);
    Long getUserSelectLectureAmount(String user_id);
}
