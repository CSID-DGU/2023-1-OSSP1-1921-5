package graduationProject.graduation_judge.domain.Graduation.controller;

import graduationProject.graduation_judge.DTO.Graduation.CoreLectureCond;
import graduationProject.graduation_judge.DTO.Graduation.CoreLectureParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationEligibilityParam;
import graduationProject.graduation_judge.domain.Graduation.service.GraduationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/result")
@RequiredArgsConstructor // 클래스 내부의 모든 final 필드 or @NonNull이 붙은 필드에 대해 생성자를 자동으로 생성
public class GraduationEligibilityController {

    @Autowired
    private final GraduationService graduationService;

    @PostMapping
    @ResponseBody
    public GraduationEligibilityParam getGraduationEligibility(@RequestBody HashMap<String, Object> requestBody) { //졸업 자격에 대한 결과 리턴
        String email = (String) requestBody.get("email");
        log.info("requested user_email is = {}", email);

        GraduationEligibilityParam result = graduationService.getGraduationEligibilityParam(email);
        log.info("test Result!!!: " + result.getRegister());

        return result;
    }

    @PostMapping("/essLectures")
    @ResponseBody
    public CoreLectureParam getEssLecturesCompletion(@RequestBody HashMap<String, Object> requestBody) { //필수 수강 강의 이수에 대한 결과 리턴
        String email = (String) requestBody.get("email");
        log.info("requested user_email is = {}", email);

        CoreLectureParam userEssCompletion = graduationService.checkEssLectureCompletion(email);
        return userEssCompletion;
    }

}