package graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetLectureInfoIncludeSemesterDTO {
    private String year;
    private String semester;
    private List<GetLectureInfoListDTO> lectureDataList;
}


