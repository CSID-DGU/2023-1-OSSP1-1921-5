package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.SecurityCodeOfUserMail;
import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DTO.MailDTO;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("비밀번호 찾기 위한 보안코드: ");
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    public SecurityCodeOfUserMail toEntity(MailDTO mailDTO) {
        // MailDTO to Entity
        return new SecurityCodeOfUserMail(mailDTO.getAddress(), mailDTO.getMessage());
    }
}