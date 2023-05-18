package graduationProject.graduation_judge;

import graduationProject.graduation_judge.DAO.MemberDao;
import graduationProject.graduation_judge.domain.Member.service.EmailService;
import graduationProject.graduation_judge.domain.Member.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberDao memberDao;
    private final EmailService emailService;
    public SpringConfig(MemberDao memberDAO, EmailService emailService) {
        this.memberDao = memberDAO;
        this.emailService = emailService;
    }

    @Bean
    public MemberServiceImpl memberService() {
        return new MemberServiceImpl(memberDao, emailService);
    }
//맞는지모르겠음
}
