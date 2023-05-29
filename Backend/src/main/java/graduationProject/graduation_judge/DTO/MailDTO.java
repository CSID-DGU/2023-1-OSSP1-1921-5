package graduationProject.graduation_judge.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class MailDTO {
    private String address;
    private String message;
}
