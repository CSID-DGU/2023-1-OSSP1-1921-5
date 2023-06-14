package graduationProject.graduation_judge.DTO.Stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTermList {
    private String email;
    private int Semester;
    private List<String> TNumList;
}
