package graduation_spring_test.demo;

import graduation_spring_test.demo.DAO.MemberDao;
import graduation_spring_test.demo.domain.Member.service.EmailService;
import graduation_spring_test.demo.domain.Member.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberDao memberDAO;
    private final EmailService emailService;

    public SpringConfig(MemberDao memberDAO, EmailService emailService) {
        this.memberDAO = memberDAO;
        this.emailService = emailService;
    }

    @Bean
    public MemberServiceImpl memberService() {
        return new MemberServiceImpl(memberDAO, emailService);
    }
//맞는지모르겠음

}
