package graduationProject.graduation_judge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoLectureDTO {
    private String lectureNick;
    private String curriculum;
    private String classArea;
    private  int classCredit;
    private String classNumber;
}
