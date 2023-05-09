package graduation_spring_test.demo.domain.Member.controller;

import graduation_spring_test.demo.domain.Member.Member;
import graduation_spring_test.demo.domain.Member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //회원 가입 페이지 뷰
    @GetMapping("/signup")
    public String signupView(Model model) {
        model.addAttribute("member", new Member());
        return "signup";
    }

    //회원 가입 로직 처리
    @PostMapping("/signup")
    public String signup(Member member) {
        try {
            memberService.register(member);
            return "redirect:/member/signin";
        } catch (Exception e) {
            return "redirect:/member/signup";
        }
    }

    //로그인 페이지 뷰
    @GetMapping("/signin")
    public String loginView() {
        return "signin";
    }

    //회원 정보 페이지 뷰 + 수정
    @GetMapping("/{memberId}/edit")
    public String mypageView(@PathVariable String memberId, Model model) {
        Member member = memberService.getMemberById(memberId);
        model.addAttribute("member", member);
        return "mypage";
    }

    // 회원 정보 수정 로직 처리
    @PostMapping("/{memberId}/edit")
    public String editMember(@PathVariable String memberId, Member member) {
        try {
            memberService.updateMember(member);
            return "redirect:/member/" + memberId;
        } catch (Exception e) {
            return "redirect:/member/" + memberId + "/edit";
        }
    }

    // 회원 탈퇴 로직 처리
    @PostMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable String memberId) {
        try {
            Member member = memberService.getMemberById(memberId);
            memberService.deleteMember(member);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/member/" + memberId;
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
