package graduationProject.graduation_judge.DTO.Member.ShowUserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendUserInfoDTO {
    private String email;
    private String pw;
    private String studentNumber;
    private String semester;
}
