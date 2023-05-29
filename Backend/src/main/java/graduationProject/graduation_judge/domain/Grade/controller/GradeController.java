package graduationProject.graduation_judge.domain.Grade.controller;

import graduationProject.graduation_judge.DTO.GradeDTO;
import graduationProject.graduation_judge.DTO.UserGradeList;
import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/input")
public class GradeController {

    @Autowired
    private final GradeService gradeService;

    //성적파일 입력
    @PostMapping("/gradeFile")
    public ResponseEntity<String> inputFile(@RequestBody UserGradeList userGradeList){
        try{
            System.out.println(userGradeList.toString());
            String email = userGradeList.getEmail();
            List<UserGradeList.GradeData> gradeDataList = userGradeList.getUserDataList();

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
