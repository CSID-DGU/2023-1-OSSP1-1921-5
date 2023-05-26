package graduationProject.graduation_judge.domain.Member.controller;

import graduationProject.graduation_judge.DAO.UserInfo;
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
    public ResponseEntity<?> signup(@RequestBody UserInfo userInfo) {
        try {
            memberService.register(userInfo);
            return ResponseEntity.ok(userInfo); //userInfo객체를 JSON형태로 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //예외 메시지 반환
        }
    }

    //회원 정보 조회
    @PostMapping("/{memberId}/show")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            UserInfo userInfo = memberService.getMemberById(id);
            return ResponseEntity.ok(userInfo); //userInfo객체를 JSON형태로 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //예외 메시지 반환
        }
    }

    //회원 정보 수정
    @PostMapping("/{memberId}/update")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserInfo userInfo) {
        try {
            memberService.updateMember(userInfo);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 정보 삭제
    @PostMapping("/{memberId}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            UserInfo userInfo = memberService.getMemberById(id);
            memberService.deleteMember(userInfo);
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
            memberService.findPassword(email, securityCode, inputSecurityCode, newPassword);
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
        UserInfo userInfo = new UserInfo("user20171@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, 115, English_level.S3);
        this.memberService.register(userInfo);
        return "input test";
    }

    @PostMapping("/emailTest")
    public String emailTest() {
        UserInfo userInfo = new UserInfo("dabin6469@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, 115, English_level.S3);
        memberService.sendSecurityCodeToEmail(userInfo.getId());

        return "email test";
    }
}
