package graduationProject.graduation_judge.DTO.DataSet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class GetDataSetInfo {
    private int dataNum; // 생성 data 개수
    private int admissionYear; // 입학년도
    private int completeSem; // 이수학기
    private Map<String, String> subjects; // 이수강의정보
}
