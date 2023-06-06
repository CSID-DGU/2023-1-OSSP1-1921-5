package graduationProject.graduation_judge.DTO.Member.Update;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUpdateInfoDTO {
    private String email;
    private String year;
    private String register;
    private String course;
    private String english;
    private String score;
}
