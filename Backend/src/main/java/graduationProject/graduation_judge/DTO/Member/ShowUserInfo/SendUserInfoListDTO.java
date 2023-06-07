package graduationProject.graduation_judge.DTO.Member.ShowUserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SendUserInfoListDTO {
    private List<SendUserInfoDTO> userInfoDTOS;
}
