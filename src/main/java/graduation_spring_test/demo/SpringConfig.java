package graduation_spring_test.demo;

import graduation_spring_test.demo.DAO.MemberDAOImpl;
import graduation_spring_test.demo.DAO.MemberDao;
import graduation_spring_test.demo.domain.Member.controller.MemberController;
import graduation_spring_test.demo.domain.Member.service.EmailService;
import graduation_spring_test.demo.domain.Member.service.EmailServiceImpl;
import graduation_spring_test.demo.domain.Member.service.MemberService;
import graduation_spring_test.demo.domain.Member.service.MemberServiceImpl;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

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
