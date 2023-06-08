package graduationProject.graduation_judge.domain.Stats.controller;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DTO.GraphInfo;
import graduationProject.graduation_judge.DTO.ScoreStatDTO;
import graduationProject.graduation_judge.DTO.SemesterInfoList;
import graduationProject.graduation_judge.DTO.UserTermList;
import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import graduationProject.graduation_judge.domain.Member.service.MemberService;
import graduationProject.graduation_judge.domain.Stats.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private StatsService statsService;

    // user의 전체 총 이수학점, 총 이수과목 수, 전체 평점, 이수학기 수
    @PostMapping("/entire")
    public ResponseEntity<?> getUserStat(@RequestBody Map<String, String> request){
        String email = request.get("email");
        int credit = 0; // 사용자 총 이수학점
        float classScore = 0; // 사용자 전체 평점
        int semester = 0; // 사용자 이수학기 수
        List<String> TNumList = new ArrayList<>(); //사용자가 이수한 학기 리스트
        UserTermList userTermList = new UserTermList(); //반환 data
        userTermList.setEmail(email);

        try{
            // userSelectList가 비어있을 경우, 통계를 낼 수 없는 예외 처리
            if(gradeService.isEmptyUserSeletList()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("통계를 낼 데이터가 없습니다.");
            }
            
            credit = gradeService.getClassCredit(email, null, null);
            //count = gradeService.getCompletedCourseCount(email);
            classScore = gradeService.getClassScore(email, null,null);
            semester = memberService.getMemberById(email).getSemester();
            userTermList.setSemester(semester);

            //TNumList
            TNumList = gradeService.getTermList(email);
            userTermList.setTNumList(TNumList);

            //update scorestat
            ScoreStatDTO scoreStatDTO = new ScoreStatDTO(email,0,"전체", classScore, credit);
            statsService.insertScoreStat(scoreStatDTO);
            return ResponseEntity.ok().body(userTermList);

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // '/stats'
    // user의 학기마다 이수학점, 이수과목 수, 전체 평점, 전공이수학점, 전공 이수과목 수, 전공 평점 json 반환
    @PostMapping("/semester")
    public ResponseEntity<?> getUserStatBySemester(@RequestBody UserTermList userTermList){
        try{
            String email = userTermList.getEmail();
            List<String> TNumList= userTermList.getTNumList();
            int semester = userTermList.getSemester(); // 이수학기 수
            SemesterInfoList semesterInfoList = new SemesterInfoList();
            List<SemesterInfoList.SemesterInfo> semesterInfos = new ArrayList<>();

            String curSem;
            int credit = 0; // 특정 학기 총 이수학점
            int majorCredit = 0; // 특정 학기 전공 이수 학점
            float classScore = 0.f; // 특정 학기 전체 평점
            float majorClassScore = 0.f; // 특정 학기 전공 평점

            for(int sem=0; sem<semester; sem++){
                curSem = TNumList.get(sem); // 현재 계산하는 학기
                credit = gradeService.getClassCredit(email, curSem, null); // 특정 학기 총 이수학점
                majorClassScore = gradeService.getClassScore(email, curSem, "전공"); // 특정 학기 전공 평점
                classScore = gradeService.getClassScore(email, curSem, null); // 특정 학기 전체 평점
                majorCredit = gradeService.getClassCredit(email, curSem, "전공"); // 특정 학기 전공 이수학점

                //update scorestat
                statsService.insertScoreStat(new ScoreStatDTO(email,sem+1,"전체", classScore, credit));
                statsService.insertScoreStat(new ScoreStatDTO(email,sem+1,"전공", majorClassScore, majorCredit));

                // return 값 생성
                SemesterInfoList.SemesterInfo semesterInfo = new SemesterInfoList.SemesterInfo(curSem, credit, majorCredit, classScore, majorClassScore);
                semesterInfos.add(semesterInfo);
            }
            return ResponseEntity.ok().body(new SemesterInfoList(email, semesterInfos));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // '/getstats'
    // 업데이트된 scorestat 값을 가지고 그래프 data 만들기. 학기마다 모든 user의 전체 평점, 전공평점, 이수학점
    @PostMapping("/getstats")
    public ResponseEntity<?> getStatGraph(@RequestBody Map<String, String> request){
        List<GraphInfo.GraphData> entireData = new ArrayList<>();
        List<GraphInfo.GraphData> majorData = new ArrayList<>();
        List<GraphInfo.GraphData> creditData = new ArrayList<>();
        try{
            String email = request.get("email");
            for(int sem=1; sem<=8; sem++){
                entireData.add(statsService.getGradeGraphInfo(sem, email, "전체"));
                majorData.add(statsService.getGradeGraphInfo(sem, email, "전공"));
                creditData.add(statsService.getCreditGraphInfo(sem, email));
            }
            GraphInfo graphInfo = new GraphInfo(entireData, majorData, creditData);
            return ResponseEntity.ok().body(new GraphInfo(entireData, majorData, creditData));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
