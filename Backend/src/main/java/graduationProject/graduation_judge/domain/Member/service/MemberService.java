package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DTO.Member.EmailCheck.GetEmailDTO;
import graduationProject.graduation_judge.DTO.Member.MyPage.SendMyPageInfoDTO;
import graduationProject.graduation_judge.DTO.Member.SendEmail.SendEmailCodeDTO;
import graduationProject.graduation_judge.DTO.Member.ShowUserInfo.SendUserInfoDTO;
import graduationProject.graduation_judge.DTO.Member.ShowUserInfo.SendUserInfoListDTO;
import graduationProject.graduation_judge.DTO.Member.SignIn.GetSignInDTO;
import graduationProject.graduation_judge.DTO.Member.SignIn.SendSignInCheckDTO;
import graduationProject.graduation_judge.DTO.Member.SignUp.GetSignUpDTO;
import graduationProject.graduation_judge.DTO.Member.Update.GetUpdateInfoDTO;
import graduationProject.graduation_judge.DTO.Member.UserInfoDTO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    int emailCheck(String id); //이메일확인
    void register(GetSignUpDTO getSignUpDTO);  // 회원 가입
    SendSignInCheckDTO login(GetSignInDTO getSignInDTO);    //로그인

    UserInfoDTO getMemberById(String id);  // 회원 조회

    SendMyPageInfoDTO getMyPageInfoById(GetEmailDTO getEmailDTO);

    SendUserInfoDTO getUserInfoDTOById(GetEmailDTO getEmailDTO);
    void updateMember(GetUpdateInfoDTO getUpdateInfoDTO); // 회원 수정

    /*void findPassword(String id, String inputSecurityCode, String newPassword); //비밀번호 찾기*/
    void changePassword(GetSignInDTO getSignInDTO);//비밀번호 변경
    void deleteMember(String id); // 회원 탈퇴(삭제)

    //보안 코드를 이메일로 전송하는 메서드
    SendEmailCodeDTO sendSecurityCodeToEmail(String id);

    SendUserInfoListDTO showAllUser(); //전체 회원 조회

}
