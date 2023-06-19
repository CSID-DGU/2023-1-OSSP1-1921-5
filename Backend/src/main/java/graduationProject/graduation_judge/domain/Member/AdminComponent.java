package graduationProject.graduation_judge.domain.Member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:adminsettings.properties")
public class AdminComponent {

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    public String getAdminEmail(){
        return adminEmail;
    }
}
