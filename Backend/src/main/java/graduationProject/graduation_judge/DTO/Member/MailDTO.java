package graduationProject.graduation_judge.DTO.Member;

import graduationProject.graduation_judge.DAO.SecurityCodeOfUserMail;
import lombok.*;

@AllArgsConstructor
@Data
public class MailDTO {
    private String address;
    private String message;

    public SecurityCodeOfUserMail toEntity() {
        return SecurityCodeOfUserMail.builder()
                .id(address)
                .securityCode(message)
                .build();
    }
}
