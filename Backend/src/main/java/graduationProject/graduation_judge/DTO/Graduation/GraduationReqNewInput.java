package graduationProject.graduation_judge.DTO.Graduation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraduationReqNewInput
{
    @JsonProperty("이수구분")
    private String category;
    @JsonProperty("학수강좌번호")
    private String lectureNumber;
    @JsonProperty("학점")
    private int credit;
    @JsonProperty("교과목명")
    private String lectureName;
    @JsonProperty("비고")
    private String comment;
    @JsonProperty("신설년도")
    private int creation_year;
}