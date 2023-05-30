package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void register(UserInfoDTO userInfoDTO);  // 회원 가입
    UserInfoDTO login(String id, String pincode);    //로그인

    UserInfoDTO getMemberById(String id);  // 회원 조회

    void updateMember(UserInfoDTO userInfoDTO); // 회원 수정

    void findPassword(String id, String inputSecurityCode, String newPassword); //비밀번호 찾기

    void deleteMember(UserInfoDTO userInfoDTO); // 회원 탈퇴(삭제)

    //보안 코드를 이메일로 전송하는 메서드
    void sendSecurityCodeToEmail(String id);

    UserInfo toEntity(UserInfoDTO userInfoDTO);
}
