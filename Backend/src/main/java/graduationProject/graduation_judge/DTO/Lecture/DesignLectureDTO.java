package graduationProject.graduation_judge.DTO.Lecture;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DesignLectureDTO {
    private String termNum;
    private String classNum;
    private Float designCredit;
}
