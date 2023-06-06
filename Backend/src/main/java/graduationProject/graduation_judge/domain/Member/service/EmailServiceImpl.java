package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.SecurityCodeOfUserMail;
import graduationProject.graduation_judge.DTO.Member.MailDTO;
import graduationProject.graduation_judge.domain.Member.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private JavaMailSender javaMailSender;
    private MailRepository mailRepository;


    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, MailRepository mailRepository) {
        this.javaMailSender = javaMailSender;
        this.mailRepository = mailRepository;
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
    public MailDTO getMailMemberById(String id) {
        // MailDTO 조회 로직 구현
        SecurityCodeOfUserMail securityCodeOfUserMail = mailRepository.findSecurityCodeOfUserMailById(id);
        if(securityCodeOfUserMail == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        MailDTO mailDTO = new MailDTO(securityCodeOfUserMail.getId(),
                securityCodeOfUserMail.getSecurityCode());
        return mailDTO;
    }

    @Override
    public void deleteMailDTO(MailDTO mailDTO) {
        //MailDTO 삭제
        mailRepository.delete(mailDTO.toEntity());
    }

}