package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.SecurityCodeOfUserMail;
import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DTO.MailDTO;
import graduationProject.graduation_judge.DTO.UserInfoDTO;

public interface EmailService {
    public void sendEmail(String to, String text);
    SecurityCodeOfUserMail toEntity(MailDTO mailDTO);
}
