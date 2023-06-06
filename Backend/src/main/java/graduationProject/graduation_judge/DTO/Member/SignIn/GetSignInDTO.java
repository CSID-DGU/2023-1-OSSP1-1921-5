package graduationProject.graduation_judge.DTO.Member.SignIn;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSignInDTO {
    private String email;
    private String pw;
}
