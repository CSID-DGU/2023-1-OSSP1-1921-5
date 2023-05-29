package graduationProject.graduation_judge.domain.Member.controller;

import graduationProject.graduation_judge.DTO.UserInfoDTO;
import graduationProject.graduation_judge.domain.Member.service.MemberService;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserInfoDTO userInfoDTO) {
        try {
            memberService.register(userInfoDTO);
            return ResponseEntity.ok(userInfoDTO); //userInfoDTO객체를 JSON형태로 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //예외 메시지 반환
        }
    }

    //회원 정보 조회
    @PostMapping("/{memberId}/mypage")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            UserInfoDTO userInfoDTO = memberService.getMemberById(id);
            return ResponseEntity.ok(userInfoDTO); //userInfo객체를 JSON형태로 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //예외 메시지 반환
        }
    }

    //회원 정보 수정
    @PostMapping("/{memberId}/update")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserInfoDTO userInfoDTO) {
        try {
            memberService.updateMember(userInfoDTO);
            return ResponseEntity.ok(userInfoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 정보 삭제
    @PostMapping("/{memberId}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            UserInfoDTO userInfoDTO = memberService.getMemberById(id);
            memberService.deleteMember(userInfoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //비밀번호 찾기
    @PostMapping("/{memberId}/forgot-password")
    public ResponseEntity<String> findPassword(@PathVariable String email,
                                               @RequestParam String securityCode,
                                               @RequestParam String inputSecurityCode,
                                               @RequestParam String newPassword) {

        try {
            memberService.findPassword(email, inputSecurityCode, newPassword);
            //보안코드 확인 후 비밀번호 변경
            return ResponseEntity.ok("비밀번호 변경이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{memberId}/send-security-code")
    public ResponseEntity<String> sendSecurityCode(@PathVariable String id) {

        try {
            memberService.sendSecurityCodeToEmail(id);
            return ResponseEntity.ok("이메일로 보안 코드가 전송되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * -
     *     - 회원가입 (등록)
     *     - 비밀번호 찾기 (수정) (수정 )
     *     - 회원 정보 조회(마이페이지 조회) (조회)
     *     - 회원 정보 수정 (수정)
     *
     *     - 로그인
     *     - ~~(회원 탈퇴)~~
     */
    @PostMapping("/test")
    public String test(){
        UserInfoDTO userInfoDTO = new UserInfoDTO("user20171@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, 115, English_level.S3);
        this.memberService.register(userInfoDTO);
        return "input test";
    }

    @PostMapping("/emailTest")
    public String emailTest() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("dabin6469@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, 115, English_level.S3);
        memberService.register(userInfoDTO);
        memberService.sendSecurityCodeToEmail(userInfoDTO.getId());
        return "email test";
    }
}
