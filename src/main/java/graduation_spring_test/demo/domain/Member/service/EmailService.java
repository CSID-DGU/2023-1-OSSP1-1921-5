package graduation_spring_test.demo.domain.Member.service;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
}
