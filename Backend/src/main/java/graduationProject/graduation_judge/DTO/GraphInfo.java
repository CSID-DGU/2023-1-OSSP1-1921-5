package graduationProject.graduation_judge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GraphInfo {
    private List<GraphData> entireData;
    private List<GraphData> majorData;
    private List<GraphData> creditData;

    @AllArgsConstructor
    @Data
    public static class GraphData{
        private int semester;
        private Number myData; //float or int 가능
        private Number avgData; //float or int 가능
    }
}
