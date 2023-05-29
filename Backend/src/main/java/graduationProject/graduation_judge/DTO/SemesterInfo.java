package graduationProject.graduation_judge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SemesterInfo {
    private String email;
    private String semester;
    private String Count;
    private String MajorCount;
    private String Credit;
    private String MajorCredit;
    private String ClassScore;
    private String MajorClassScore;
}
