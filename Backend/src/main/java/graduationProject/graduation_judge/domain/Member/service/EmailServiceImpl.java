package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.SecurityCodeOfUserMail;
import graduationProject.graduation_judge.DTO.MailDTO;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
import graduationProject.graduation_judge.domain.Member.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final MailRepository mailRepository;

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
        if(mailRepository.findById(id) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        return mailRepository.findById(id);
    }

    @Override
    public void deleteMailDTO(MailDTO mailDTO) {
        //MailDTO 삭제
        mailRepository.delete(toEntity(mailDTO));
    }

    @Override
    public SecurityCodeOfUserMail toEntity(MailDTO mailDTO) {
        // MailDTO to Entity
        return new SecurityCodeOfUserMail(mailDTO.getAddress(), mailDTO.getMessage());
    }
}