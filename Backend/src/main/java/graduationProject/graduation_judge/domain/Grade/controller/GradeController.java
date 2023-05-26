package graduationProject.graduation_judge.domain.Grade.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import graduationProject.graduation_judge.DTO.GradeDTO;
import graduationProject.graduation_judge.DTO.UserDatas;
import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/input")
public class GradeController {

    @Autowired
    private GradeService gradeService;



    // RequestBody UserDatas json은 아래와 같다.
    //[   {"email": "<user_email>"},
    //    {"CNumber": "<course_number>-0<division_number>",
    //      "ClassScore": "<class_score>",
    //      "TNumber": "<term_number>"
    //    }, ... 반복
    //]
    //성적파일 입력
    @PostMapping()
    public ResponseEntity<String> inputFile(@RequestBody UserDatas userDatas){
        try{
            String email = userDatas.getEmail();
            List<UserDatas.UserData> userDataList = userDatas.getUserDataList();

            for (int i = 0; i < userDataList.size(); i++) {
                String TNumber = userDataList.get(i).getTNumber();
                String CNumber = userDataList.get(i).getCNumber();
                String ClassScore = userDataList.get(i).getClassScore();
                GradeDTO gradeDTO = new GradeDTO(email, TNumber, CNumber, ClassScore);
                gradeService.inputGrade(gradeDTO);

            }

            // 이미 입력된 성적 파일이 있는지 True/False 반환
            boolean hasResult = gradeService.isExistGrade(email);
            System.out.println(": 결과가 " + (hasResult ? "있음" : "없음"));

            return ResponseEntity.ok().body(String.valueOf(hasResult));

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //DB test
    @GetMapping("/test")
    public String test(){
        GradeDTO grade = new GradeDTO("11@naver.com", "2021_1", "CSE2014-02", "A+");
        this.gradeService.inputGrade(grade);
        return "input test";
    }
}
