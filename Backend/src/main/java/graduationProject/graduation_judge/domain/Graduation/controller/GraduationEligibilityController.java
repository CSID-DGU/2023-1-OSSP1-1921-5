package graduationProject.graduation_judge.domain.Graduation.controller;

import graduationProject.graduation_judge.DTO.EssLectureResult;
import graduationProject.graduation_judge.DTO.GraduationResultList;
import graduationProject.graduation_judge.domain.Graduation.service.GraduationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor // 클래스 내부의 모든 final 필드 or @NonNull이 붙은 필드에 대해 생성자를 자동으로 생성
public class GraduationEligibilityController {

    @Autowired
    private final GraduationService graduationService;

    @GetMapping
    @ResponseBody
    public GraduationResultList getGraduationEligibility() { //졸업 자격에 대한 결과 리턴
        GraduationResultList userEligibility = graduationService.checkGraduationEligibility();
        return userEligibility;
    }

    @GetMapping("/essLectures")
    @ResponseBody
    public EssLectureResult getEssLecturesCompletion() { //필수 수강 강의 이수에 대한 결과 리턴
        EssLectureResult userEssCompletion = graduationService.checkEssLectureCompletion();
        return userEssCompletion;
    }

}