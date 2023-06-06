package graduationProject.graduation_judge.DTO.DataSet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetDataSetInfo {
    private String datasetNumber;
    private String year;
    private String semester;
    private String constraints;
}
