package graduationProject.graduation_judge.domain.Member.controller;

import graduationProject.graduation_judge.DTO.Member.UserInfoDTO;
import graduationProject.graduation_judge.domain.Member.service.MemberService;

import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //회원가입 시 중복아이디 확인
    @PostMapping("/emailcheck")
    public HashMap<String, Integer> emailCheck(@RequestBody Map<String ,String > request){
        String email = request.get("email");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("result", memberService.emailCheck(email));
        return map;
    }

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody  Map<String, String > request) {
        try {
            UserInfoDTO userInfoDTO = new UserInfoDTO(request.get("email"),
                    request.get("pw"), Integer.parseInt(request.get("semester")),
                    Integer.parseInt(request.get("year")),
                    Major_curriculum.valueOf(request.get("course")),
                    Integer.parseInt(request.get("score")),
                    English_level.valueOf(request.get("english")));
            memberService.register(userInfoDTO);
            return ResponseEntity.ok(userInfoDTO);
            //userInfoDTO객체를 JSON형태로 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            //예외 메시지 반환
        }
    }

    //로그인
    @PostMapping("/signin")
    public HashMap<String, Object> signin(@RequestBody Map<String, String > request) {
        try {
            String email = request.get("email");
            String pw = request.get("pw");
            String id = memberService.login(email, pw);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            return map; //id를 JSON형태로 반환

        }catch (Exception e){
            HashMap<String, Object> map = new HashMap<>();
            map.put("error", e);
            return map;
        }
    }

    //회원 정보 조회
    @PostMapping("/mypage")
    public HashMap<String,Object> getUserById(@RequestBody Map<String,String> request) {
        try {
            String id = request.get("email");

            UserInfoDTO userInfoDTO = memberService.getMemberById(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("Semester", String.valueOf(userInfoDTO.getSemester()));
            map.put("StudentNumber", String.valueOf(userInfoDTO.getStudent_number()));
            map.put("Course", String.valueOf(userInfoDTO.getCourse()));
            map.put("EnglishGrade", String.valueOf(userInfoDTO.getEnglishGrade()));
            map.put("Score", String.valueOf(userInfoDTO.getToeicScore()));
            return map;
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("error", e);
            return map;
        }
    }

    //회원 정보 수정
    @PostMapping("/updateuserinfo")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String > request) {
        String id = request.get("email");
        int studentNumber = Integer.parseInt(request.get("year"));
        int semester = Integer.parseInt(request.get("register"));
        Major_curriculum course = Major_curriculum.valueOf(request.get("course"));
        English_level englishGrade = English_level.valueOf(request.get("english"));
        int toeicScore = Integer.parseInt(request.get("score"));
        try {
            memberService.updateMember(id, semester, studentNumber, course,
                    toeicScore, englishGrade); //id, pincode빼고 수정
            UserInfoDTO userInfoDTO = memberService.getMemberById(id);
            return ResponseEntity.ok(userInfoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //회원 정보 삭제
    @PostMapping("/deletepf")
    public ResponseEntity<?> deleteUser(@RequestBody HashMap<String,String> request) {
        String id = request.get("email");
        try {
            memberService.deleteMember(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //비밀번호 변경
    @PostMapping("/changepw")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newpw = request.get("pw");
        try {
            memberService.changePassword(email, newpw);
            return ResponseEntity.ok("비밀번호 변경 완료");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //비밀번호 변경시 이메일 확인
    @PostMapping("/isthereemail")
    public HashMap<String, Integer> isThereEmail(@RequestBody HashMap<String, String> request) {
        String email = request.get("email");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("result", memberService.emailCheck(email));
        return map;
    }

    //email로 보안코드 전송
    @PostMapping("/sendemail")
    public HashMap<?,?> sendSecurityCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        try {
            String number = memberService.sendSecurityCodeToEmail(email);
            HashMap<String, String> map = new HashMap<>();
            map.put("number", number);
            return map;
        } catch (IllegalArgumentException e) {
            return (HashMap<?, ?>) new HashMap<>().put(e.getMessage(), e);
        } catch (RuntimeException e) {
            return (HashMap<?, ?>) new HashMap<>().put(e.getMessage(), e);
        }
    }

}
