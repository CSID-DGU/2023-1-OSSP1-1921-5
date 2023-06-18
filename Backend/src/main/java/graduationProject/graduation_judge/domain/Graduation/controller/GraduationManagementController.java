package graduationProject.graduation_judge.domain.Graduation.controller;

import graduationProject.graduation_judge.DTO.Graduation.CoreLectureParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationReqInput;
import graduationProject.graduation_judge.DTO.Graduation.GraduationReqModiInput;
import graduationProject.graduation_judge.DTO.Graduation.GraduationReqNewInput;
import graduationProject.graduation_judge.domain.Graduation.service.GraduationManageService;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/change")
@RequiredArgsConstructor // 클래스 내부의 모든 final 필드 or @NonNull이 붙은 필드에 대해 생성자를 자동으로 생성
public class GraduationManagementController {

    @Autowired
    private final GraduationManageService graduationManageService;

    @PostMapping("/file")
    @ResponseBody
    public ResponseEntity<String> AddFile(
            @RequestHeader(value = "X-File-Type") String reqinfo,
            @RequestBody Map<String, GraduationReqInput> reqInput
    ) {
        log.warn("here you are = {}", reqInput);

        Major_curriculum mj = null;
        String enrollment = null;

        if(reqinfo.contains("hard")) {
            mj = Major_curriculum.심화;
            enrollment = reqinfo.replaceAll("[^0-9]", "");
        }
        if(reqinfo.contains("general")) {
             mj = Major_curriculum.일반;
            enrollment = reqinfo.replaceAll("[^0-9]", "");
        }


        //졸업 요건 변경
        graduationManageService.addGraduationRequirement(reqInput, Integer.parseInt(enrollment), mj);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping("/newfile")
    @ResponseBody
    public ResponseEntity<String> AddNewFile(
            @RequestHeader(value = "X-File-Type") String reqinfo,
            @RequestBody Map<String, GraduationReqNewInput> reqInput
    ) {
        log.warn("here you are = {}", reqInput);

        Major_curriculum mj = null;
        String enrollment = null;

        if(reqinfo.contains("hard")) {
            mj = Major_curriculum.심화;
            enrollment = reqinfo.replaceAll("[^0-9]", "");
        }
        if(reqinfo.contains("general")) {
            mj = Major_curriculum.일반;
            enrollment = reqinfo.replaceAll("[^0-9]", "");
        }


        //신설과목 추가
        graduationManageService.addNewGraduationRequirement(reqInput, Integer.parseInt(enrollment), mj);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/changefile")
    @ResponseBody
    public ResponseEntity<String> ChangeFile(
            @RequestHeader(value = "X-File-Type") String reqinfo,
            @RequestBody Map<String, GraduationReqModiInput> reqInput
    ) {
        log.warn("here you are = {}", reqInput);

        Major_curriculum mj = null;
        String enrollment = null;

        if(reqinfo.contains("hard")) {
            mj = Major_curriculum.심화;
            enrollment = reqinfo.replaceAll("[^0-9]", "");
        }
        if(reqinfo.contains("general")) {
            mj = Major_curriculum.일반;
            enrollment = reqinfo.replaceAll("[^0-9]", "");
        }


        //신설과목 추가
//        graduationManageService.addNewGraduationRequirement(reqInput, Integer.parseInt(enrollment), mj);


        return new ResponseEntity<>(HttpStatus.OK);
    }
}


















