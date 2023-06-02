package graduationProject.graduation_judge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterInfoList {
    private String email;
    private List<SemesterInfo> SemesterInfos;

    @Data
    @AllArgsConstructor
    public static class SemesterInfo{
        private String semester;
        private int Count;
        private int MajorCount;
        private int Credit;
        private int MajorCredit;
        private float ClassScore;
        private float MajorClassScore;
    }

}
