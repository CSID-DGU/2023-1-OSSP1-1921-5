package graduationProject.graduation_judge.domain.Lecture.controller;


import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import graduationProject.graduation_judge.domain.Lecture.Service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
//@RequestMapping("/adminpage")
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @PostMapping("/uploadNewSemester")
    public ResponseEntity<?> uploadNewSemester(@RequestBody GetLectureInfoIncludeSemesterDTO getLectureDTO){

        try{
            lectureService.inputLecture(getLectureDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
