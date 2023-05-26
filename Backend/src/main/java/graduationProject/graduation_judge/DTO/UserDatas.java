package graduationProject.graduation_judge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDatas {
    private String email;
    private List<UserData> userDataList;

    @Data
    public static class UserData{
        private String TNumber;
        private String CNumber;
        private String ClassScore;
    }
}
