package graduationProject.graduation_judge.DTO.Graduation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraduationReqModiInput {
    private String prevLectureNumber;
    private String newLectureNumber;
    private String lectureName;
    private int creation_year;
    private int credit;

}
