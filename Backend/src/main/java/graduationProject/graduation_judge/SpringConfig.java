package graduationProject.graduation_judge;

import graduationProject.graduation_judge.DAO.MemberDAOImpl;
import graduationProject.graduation_judge.DAO.MemberDao;
import graduationProject.graduation_judge.domain.Member.service.EmailService;
import graduationProject.graduation_judge.domain.Member.service.EmailServiceImpl;
import graduationProject.graduation_judge.domain.Member.service.MemberService;
import graduationProject.graduation_judge.domain.Member.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private jakarta.persistence.EntityManager EntityManager;
    private org.springframework.mail.javamail.JavaMailSender JavaMailSender;

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberDao(), emailService());
    }
    @Bean
    public MemberDao memberDao() {
        return new MemberDAOImpl(EntityManager);
    }

    @Bean
    public EmailService emailService() {
        return new EmailServiceImpl(JavaMailSender);
    }
}
