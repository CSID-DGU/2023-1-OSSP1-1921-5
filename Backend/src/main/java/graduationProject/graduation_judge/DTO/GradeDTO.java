package graduationProject.graduation_judge.DTO;

import graduationProject.graduation_judge.DAO.UserSelectList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class GradeDTO {
    private String memberId;
    private String termNum;
    private String classNum;
    private String score;

    public UserSelectList toEntity(){
        return new UserSelectList(this.getMemberId(), this.getTermNum(), this.getClassNum(), this.getScore());
    }
}
