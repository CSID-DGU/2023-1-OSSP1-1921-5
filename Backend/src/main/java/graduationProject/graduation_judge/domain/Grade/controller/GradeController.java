package graduationProject.graduation_judge.domain.Grade.controller;

import graduationProject.graduation_judge.DTO.Grade.GradeDTO;
import graduationProject.graduation_judge.DTO.Grade.UserGradeList;
import graduationProject.graduation_judge.DTO.Member.UserInfoDTO;
import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import graduationProject.graduation_judge.domain.Member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/input")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private MemberService memberService;

    //성적파일 입력
    @PostMapping("/gradeFile")
    public ResponseEntity<String> inputFile(@RequestBody UserGradeList userGradeList){
        try{
            String email = userGradeList.getEmail();
            List<UserGradeList.GradeData> gradeDataList = userGradeList.getUserDataList();

            // UserInfo 학기수랑 실제 파일 학기 수랑 일치하지 않으면 에러 반환
            Set<String> uniqueTermNumbers = new HashSet<>();
            for (UserGradeList.GradeData gradeData : gradeDataList) {
                uniqueTermNumbers.add(gradeData.getTermNumber());
            }

            int uniqueTermNumberCount = uniqueTermNumbers.size(); //실제 파일 학기 수
            UserInfoDTO userDTO = memberService.getMemberById(email);

            int semUserInfo = userDTO.getSemester(); //UserInfo 학기 수
            if (uniqueTermNumberCount != semUserInfo) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("학기 수가 일치하지 않습니다");
            }

            // 기존 성적 삭제
            if(gradeService.isExistGrade(email)){
                gradeService.deleteGradeByMember(email);
            }

            // 새로운 성적 입력
            for (int i = 0; i < gradeDataList.size(); i++) {
                String TNumber = gradeDataList.get(i).getTermNumber();
                String CNumber = gradeDataList.get(i).getClassNumber();
                String ClassScore = gradeDataList.get(i).getClassScore();

                GradeDTO gradeDTO = new GradeDTO(email, TNumber, CNumber, ClassScore);
                gradeService.inputGrade(gradeDTO);
            }
            
            return ResponseEntity.ok().body("성적 입력완료");

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 이미 입력된 성적 파일이 있는지 True/False 반환
    @PostMapping("/isExist")
    public ResponseEntity<?> isExist(@RequestBody Map<String, String> request){
        try{
            String email = request.get("email");
            boolean hasResult = gradeService.isExistGrade(email);

            Map<String, Boolean> response = new HashMap<>();
            response.put("result", hasResult);
            return ResponseEntity.ok().body(response);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
