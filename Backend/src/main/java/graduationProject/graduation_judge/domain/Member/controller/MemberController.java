package graduationProject.graduation_judge.domain.Member.controller;

import graduationProject.graduation_judge.domain.Member.Member;
import graduationProject.graduation_judge.domain.Member.service.MemberService;
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
    @GetMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Member member) {
        try {
            memberService.register(member);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 정보 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMemberById(@PathVariable String memberId) {
        try {
            Member member = memberService.getMemberById(memberId);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 정보 수정
    @PutMapping("/{memberId}")
    public ResponseEntity<?> updateMember(@PathVariable String memberId, @RequestBody Member member) {
        try {
            memberService.updateMember(member);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //회원 정보 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable String memberId) {
        try {
            Member member = memberService.getMemberById(memberId);
            memberService.deleteMember(member);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //비밀번호 찾기
    @PostMapping("/forgot-password")
    public ResponseEntity<String> findPassword(@RequestParam String email,
                                               @RequestParam String securityCode,
                                               @RequestParam String newPassword) {

        try {
            memberService.findPassword(email, securityCode, newPassword);
            return ResponseEntity.ok("비밀번호 변경이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/send-security-code")
    public ResponseEntity<String> sendSecurityCode(@RequestParam String email) {

        try {
            memberService.sendSecurityCodeToEmail(email);
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
}
