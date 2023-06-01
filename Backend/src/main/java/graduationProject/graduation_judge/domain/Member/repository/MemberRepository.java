package graduationProject.graduation_judge.domain.Member.repository;

import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<UserInfo, Long> {
    //UserInfoDTO findByUser_id(String id);
    UserInfoDTO findUserInfoByUser_id(String id);
}
