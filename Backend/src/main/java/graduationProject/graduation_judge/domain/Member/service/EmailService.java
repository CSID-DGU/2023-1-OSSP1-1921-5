package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DTO.Member.MailDTO;

public interface EmailService {
    public void sendEmail(String to, String text);
    public void deleteMailDTO(MailDTO mailDTO);
    public MailDTO getMailMemberById(String id);
}
