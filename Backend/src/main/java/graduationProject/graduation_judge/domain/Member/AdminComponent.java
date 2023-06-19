package graduationProject.graduation_judge.domain.Member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:adminsettings.properties")
<<<<<<< HEAD
=======

>>>>>>> 006981cec14e2b71620d360413d34d4d06220dd9
public class AdminComponent {

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    public String getAdminEmail(){
        return adminEmail;
    }
}
