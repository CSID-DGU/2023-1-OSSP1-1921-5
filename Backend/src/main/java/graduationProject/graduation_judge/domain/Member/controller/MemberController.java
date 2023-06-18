package graduationProject.graduation_judge.domain.Member.controller;

import graduationProject.graduation_judge.DTO.Member.EmailCheck.GetEmailDTO;
import graduationProject.graduation_judge.DTO.Member.EmailCheck.SendEmailCheckDTO;
import graduationProject.graduation_judge.DTO.Member.MyPage.SendMyPageInfoDTO;
import graduationProject.graduation_judge.DTO.Member.SendEmail.SendEmailCodeDTO;
import graduationProject.graduation_judge.DTO.Member.ShowUserInfo.SendUserInfoDTO;
import graduationProject.graduation_judge.DTO.Member.ShowUserInfo.SendUserInfoListDTO;
import graduationProject.graduation_judge.DTO.Member.SignIn.GetSignInDTO;
import graduationProject.graduation_judge.DTO.Member.SignIn.SendSignInCheckDTO;
import graduationProject.graduation_judge.DTO.Member.SignUp.GetSignUpDTO;
import graduationProject.graduation_judge.DTO.Member.Update.GetUpdateInfoDTO;
import graduationProject.graduation_judge.domain.Member.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //회원가입 시 중복아이디 확인
    //비밀번호 변경시 이메일 확인
    @RequestMapping(value = {"/emailcheck", "/isthereemail"}, method = RequestMethod.POST)
    public ResponseEntity<?> emailCheck(@RequestBody GetEmailDTO getEmailDTO){
        try {
            Integer result = memberService.emailCheck(getEmailDTO.getEmail());
            SendEmailCheckDTO sendEmailCheckDTO = new SendEmailCheckDTO(result);
            return ResponseEntity.ok(sendEmailCheckDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody GetSignUpDTO getSignUpDTO) {
        try {
            memberService.register(getSignUpDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            //예외 메시지 반환
        }
    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody GetSignInDTO getSignInDTO) {
        try {
            SendSignInCheckDTO sendSignInCheckDTO = memberService.login(getSignInDTO);
            return ResponseEntity.ok().body(sendSignInCheckDTO); //id를 JSON형태로 반환

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 정보 조회
    @PostMapping("/mypage")
    public ResponseEntity<?> getUserById(@RequestBody GetEmailDTO getEmailDTO) {
        try {
            SendMyPageInfoDTO sendMyPageInfoDTO = memberService.getMyPageInfoById(getEmailDTO);
            return ResponseEntity.ok().body(sendMyPageInfoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 정보 수정
    @PostMapping("/updateuserinfo")
    public ResponseEntity<?> updateUser(@RequestBody GetUpdateInfoDTO getUpdateInfoDTO) {
        try {
            memberService.updateMember(getUpdateInfoDTO); //id, pincode빼고 수정
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //회원 정보 삭제
    //관리자페이지에서 회원 강제 탈퇴
    @RequestMapping(value = {"/deletepf", "/forcedeletion"}, method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@RequestBody GetEmailDTO getEmailDTO) {
        try {
            memberService.deleteMember(getEmailDTO.getEmail());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //비밀번호 변경
    @PostMapping("/changepw")
    public ResponseEntity<String> changePassword(@RequestBody GetSignInDTO getSignInDTO) {
        try {
            memberService.changePassword(getSignInDTO);
            return ResponseEntity.ok("비밀번호 변경 완료");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //email로 보안코드 전송
    @PostMapping("/sendemail")
    public ResponseEntity<?> sendSecurityCode(@RequestBody GetEmailDTO getEmailDTO) {
        try {
            SendEmailCodeDTO sendEmailCodeDTO = memberService.sendSecurityCodeToEmail(getEmailDTO.getEmail());
            return ResponseEntity.ok().body(sendEmailCodeDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/showUserInfoById") //관리자 페이지 -> 회원 목록에서 검색했을 경우
    public ResponseEntity<?> showUserInfo(@RequestBody GetEmailDTO getEmailDTO){
        try{
            SendUserInfoDTO sendUserInfoDTO = memberService.getUserInfoDTOById(getEmailDTO);
            return ResponseEntity.ok().body(sendUserInfoDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/showUserInfoList")
    public ResponseEntity<?> showUserInfoList(){
        try {
            SendUserInfoListDTO sendUserInfoListDTO = memberService.showAllUser();
            return ResponseEntity.ok().body(sendUserInfoListDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
