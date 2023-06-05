package graduationProject.graduation_judge.domain.Lecture.controller;


import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/adminpage")
public class LectureController {


    @PostMapping("/uploadNewSemester")
    public ResponseEntity<?> uploadNewSemester(@RequestBody GetLectureInfoIncludeSemesterDTO getLectureDTO){

        try{
            
            return ResponseEntity.ok().body(getLectureDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
