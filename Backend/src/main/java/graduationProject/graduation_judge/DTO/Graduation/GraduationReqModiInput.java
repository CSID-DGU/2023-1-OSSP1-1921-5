package graduationProject.graduation_judge.DTO.Graduation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraduationReqModiInput {

    @JsonProperty("기존학수강좌번호")
    private String prevLectureNumber;

    @JsonProperty("새학수강좌번호")
    private String newLectureNumber;

    @JsonProperty("교과목명")
    private String lectureName;

    @JsonProperty("년도")
    private int creation_year;

    @JsonProperty("학점")
    private int credit;

}


