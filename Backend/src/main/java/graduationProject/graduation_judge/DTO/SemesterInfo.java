package graduationProject.graduation_judge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterInfo {
    private String email;
    private String semester; //여기부터 list?
    private int Count;
    private int MajorCount;
    private int Credit;
    private int MajorCredit;
    private float ClassScore;
    private float MajorClassScore;
}
