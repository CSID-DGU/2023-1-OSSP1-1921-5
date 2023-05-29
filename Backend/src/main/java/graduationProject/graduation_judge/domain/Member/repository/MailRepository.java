package graduationProject.graduation_judge.domain.Member.repository;

import graduationProject.graduation_judge.DAO.SecurityCodeOfUserMail;
import graduationProject.graduation_judge.DTO.MailDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<SecurityCodeOfUserMail, Long> {
    MailDTO findById(String id);
}
