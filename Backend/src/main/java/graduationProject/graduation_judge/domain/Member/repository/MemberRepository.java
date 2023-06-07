package graduationProject.graduation_judge.domain.Member.repository;

import graduationProject.graduation_judge.DAO.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findUserInfoByUserid(String Userid);
    void deleteAllByUserid(String Userid);

    List<UserInfo> findAll();
}
