package graduationProject.graduation_judge.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserGradeList {
    private String email;
    private List<GradeData> userDataList;

    @Data
    @AllArgsConstructor
    public static class GradeData{
        @JsonProperty("CNumber")
        private String classNumber;
        @JsonProperty("ClassScore")
        private String classScore;
        @JsonProperty("TNumber")
        private String termNumber;
    }
}
