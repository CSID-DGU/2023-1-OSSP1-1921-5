package graduationProject.graduation_judge.DTO.Member.SignUp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSignUpDTO {
    private String email;
    private String pw;
    private String semester;
    private String year;
    private String course;
    private String score;
    private String english;
}
