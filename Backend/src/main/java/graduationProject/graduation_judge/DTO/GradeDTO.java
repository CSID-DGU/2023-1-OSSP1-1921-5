package graduationProject.graduation_judge.DTO;

import graduationProject.graduation_judge.DAO.UserSelectList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class GradeDTO {

    private String memberId;
    private String termNum;
    private String classNum;
    private String score;
}
