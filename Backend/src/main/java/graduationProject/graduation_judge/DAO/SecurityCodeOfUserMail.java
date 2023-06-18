package graduationProject.graduation_judge.DAO;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "security_code_of_user_mail")
public class SecurityCodeOfUserMail {
    @Id
    @Column(name = "user_mail_id", length = 200, nullable = false)
    private String id;

    @Column(name = "security_code", length = 200)
    private String securityCode;

    @Builder
    public SecurityCodeOfUserMail(String id, String securityCode) {
        this.id = id;
        this.securityCode = securityCode;
    }
}
