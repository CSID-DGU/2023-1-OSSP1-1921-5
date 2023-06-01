package graduationProject.graduation_judge.DTO;

import graduationProject.graduation_judge.DAO.SecurityCodeOfUserMail;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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
