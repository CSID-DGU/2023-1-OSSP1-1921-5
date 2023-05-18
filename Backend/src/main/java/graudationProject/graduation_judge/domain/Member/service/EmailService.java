package graudationProject.graduation_judge.domain.Member.service;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
}
