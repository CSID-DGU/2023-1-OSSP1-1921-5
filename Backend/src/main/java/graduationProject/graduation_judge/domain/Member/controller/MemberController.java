package graduationProject.graduation_judge.domain.Member.controller;

import graduationProject.graduation_judge.DTO.UserInfoDTO;
import graduationProject.graduation_judge.domain.Member.service.MemberService;

import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //회원가입 시 중복아이디 확인000
    @PostMapping("/emailcheck")
    public HashMap<String, Integer> emailCheck(@RequestBody Map<String ,String > request){
        String email = request.get("email");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("result", memberService.emailCheck(email));
        return map;
    }

    //회원 가입000
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

    //로그인000
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

    //회원 정보 조회000
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

    //회원 정보 수정000
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


    //비밀번호 변경000
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

    //비밀번호 변경시 이메일 확인000
    @PostMapping("/isthereemail")
    public HashMap<String, Integer> isThereEmail(@RequestBody HashMap<String, String> request) {
        String email = request.get("email");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("result", memberService.emailCheck(email));
        return map;
    }

    //email로 보안코드 전송
    @PostMapping("/sendemail")
    public ResponseEntity<Object> sendSecurityCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        try {
            String number = memberService.sendSecurityCodeToEmail(email);
            return ResponseEntity.ok(Integer.parseInt(number));
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
    /*@PostMapping("/test")
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
    }*/
}
