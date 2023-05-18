package graduationProject.graduation_judge.domain.Grade.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import graduationProject.graduation_judge.domain.Grade.Grade;
import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    //성적파일 입력
    @RequestMapping(value = "/input", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String inputFile(@RequestBody Map<String, Object> param){
        //구글의 json parser 라이브러리
        Gson gson = new Gson();

        // jsonPaserPser 클래스 객체를 만들고 해당 객체에
        JsonParser jsonParser = new JsonParser();

        // param 오브젝트 -> 문자열 파싱 -> jsonElement 파싱
        JsonElement elementEmail = jsonParser.parse(param.get("email").toString());
        JsonElement elementTNumber = jsonParser.parse(param.get("TNumber").toString());
        JsonElement elementCNumber = jsonParser.parse(param.get("CNumber").toString());
        JsonElement elementClassScore = jsonParser.parse(param.get("ClassScore").toString());
        //JsonElement elements = jsonParser.parse(param.toString());

        // JSonElement -> List<String> 파싱
        List<String> emailList = gson.fromJson(elementEmail, (new TypeToken<List<String>>() {}).getType());
        List<String> TNumberList = gson.fromJson(elementTNumber, (new TypeToken<List<String>>() {}).getType());
        List<String> CNumberList = gson.fromJson(elementCNumber, (new TypeToken<List<String>>() {}).getType());
        List<String> ClassScoreList = gson.fromJson(elementClassScore, (new TypeToken<List<String>>() {}).getType());
        //List<Grade> List = gson.fromJson(elements, (new TypeToken<List<Grade>>() {}).getType());

        for(int i=0; i<emailList.size(); i++){
            Grade grade = new Grade(emailList.get(i), TNumberList.get(i), CNumberList.get(i), ClassScoreList.get(i));
            gradeService.inputGrade(grade);
        }
        return "graduation";
    }

}
