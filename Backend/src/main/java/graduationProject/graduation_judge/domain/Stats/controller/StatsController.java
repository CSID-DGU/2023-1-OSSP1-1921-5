package graduationProject.graduation_judge.domain.Stats.controller;

import graduationProject.graduation_judge.DTO.SemesterInfo;
import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    @Autowired
    private final GradeService gradeService;

    // user의 전체 총 이수학점, 총 이수과목 수, 전체 평점, 이수학기 수
    // -> 프론트: 이수학기 수마다 data(email, TNumber, semester) 생성해 '/stats' ,'/updatestat'에 순서대로 요청
    @PostMapping("/semester")
    public ResponseEntity<?> getUserStat(@RequestBody Map<String, String> request){
        String email = request.get("email");
        int credit = 0;
        int count = 0;
        int classScore = 0;
        int semester = 0;
        //TNumList
        try{
            credit = gradeService.getTotalClassCredit(email);
            return null;
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // '/stats'
    // user의 학기마다 이수학점, 이수과목 수, 전체 평점, 전공이수학점, 전공 이수과목 수, 전공 평점 json 반환
    @PostMapping("/stats")
    public ResponseEntity<?> getUserStatBySemester(@RequestBody Map<String, String> request){
        try{
            return null;
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // '/updatestat'
    // user의 학기마다 전체 scorestat와 전공 scorestat 업데이트
    @PostMapping("/updatestat")
    public ResponseEntity<?> updateEntireStat(@RequestBody SemesterInfo semesterInfo){
        return null;
    }

    // '/getstats'
    // 업데이트된 scorestat 값을 가지고 그래프 data 만들기. 학기마다 모든 user의 전체 평점, 전공평점, 이수학점   -> 프론트:
    @PostMapping("/getstats")
    public ResponseEntity<?> getStatGraph(@RequestBody Map<String, String> request){
        String email = request.get("email");
        return null;
    }

}
