package graduationProject.graduation_judge.domain.Grade.controller;

import graduationProject.graduation_judge.DTO.GradeDTO;
import graduationProject.graduation_judge.DTO.UserGradeList;
import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/input")
public class GradeController {


    private final GradeService gradeService;

    //성적파일 입력
    @PostMapping("/inputFile")
    public ResponseEntity<String> inputFile(@RequestBody UserGradeList userGradeList){
        try{
            System.out.println(userGradeList.toString());
            String email = userGradeList.getEmail();
            List<UserGradeList.GradeData> gradeDataList = userGradeList.getUserDataList();

            for (int i = 0; i < gradeDataList.size(); i++) {
                String TNumber = gradeDataList.get(i).getTermNumber();
                String CNumber = gradeDataList.get(i).getClassNumber();
                String ClassScore = gradeDataList.get(i).getClassScore();

                GradeDTO gradeDTO = new GradeDTO(email, TNumber, CNumber, ClassScore);
                this.gradeService.inputGrade(gradeDTO);
            }

            // 이미 입력된 성적 파일이 있는지 True/False 반환
            boolean hasResult = gradeService.isExistGrade(email);
            System.out.println(": 결과가 " + (hasResult ? "있음" : "없음"));

            return ResponseEntity.ok().body(String.valueOf(hasResult));

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
