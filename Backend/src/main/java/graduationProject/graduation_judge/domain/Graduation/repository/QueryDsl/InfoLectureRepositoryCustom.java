package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import java.util.List;

public interface InfoLectureRepositoryCustom {
    List<String> getUserSelectLectureInfoList(String user_id);
    Long getUserSelectLectureAmount(String user_id);
}
