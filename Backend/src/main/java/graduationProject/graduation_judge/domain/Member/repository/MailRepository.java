package graduationProject.graduation_judge.domain.Member.repository;

import graduationProject.graduation_judge.DAO.SecurityCodeOfUserMail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<SecurityCodeOfUserMail, Long> {
    SecurityCodeOfUserMail findSecurityCodeOfUserMailById(String id);
    void deleteAllById(String id);
}
