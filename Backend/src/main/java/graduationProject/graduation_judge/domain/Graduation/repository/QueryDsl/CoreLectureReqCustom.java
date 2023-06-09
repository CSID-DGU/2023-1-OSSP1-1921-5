package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import graduationProject.graduation_judge.global.common_unit.Major_curriculum;

import java.util.List;

public interface CoreLectureReqCustom {
    List<String> getLectureList(Integer enrollment, Major_curriculum course, String category);
}
