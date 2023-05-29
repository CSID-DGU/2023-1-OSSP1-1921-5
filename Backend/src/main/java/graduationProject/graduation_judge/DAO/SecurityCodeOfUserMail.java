package graduationProject.graduation_judge.DAO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "security_code_of_user_mail")
public class SecurityCodeOfUserMail {
    @Id
    @Column(name = "user_mail_id", length = 200, nullable = false)
    private String id;

    @Column(name = "security_code")
    private String securityCode;
}
