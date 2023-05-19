package graduationProject.graduation_judge.domain.Stats.controller;

import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    @Autowired
    private final GradeService gradeService;

    // user의 전체 총 이수학점, 총 이수과목 수, 전체 평점, 이수학기 수
    // -> 프론트: 이수학기 수마다 data(email, TNumber, semester) 생성해 '/stats' ,'/updatestat'에 순서대로 요청
    @GetMapping("/semester")
    @ResponseBody
    public void userScoreStat(){

    }

    // '/stats'
    // user의 학기마다 이수학점, 이수과목 수, 전체 평점, 전공이수학점, 전공 이수과목 수, 전공 평점

    // '/updatestat'
    // user의 학기마다 전체 scorestat와 전공 scorestat 업데이트

    // '/getstats'
    // 업데이트된 scorestat 값을 가지고 그래프 data 만들기. 학기마다 모든 user의 전체 평점, 전공평점, 이수학점   -> 프론트:
}
