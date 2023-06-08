package graduationProject.graduation_judge.domain.DataSet.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
@RequestMapping("/dataset")
public class DataSetController {
    private static final String FilePath = "../../../../../../../../testDataSet/data";
    @PostMapping("/create")
    public ResponseEntity<?> createDataSet(@RequestBody Map<String, String> request){
        // test data set 명령어 입력 받기
        // testDataset python 으로 명령어 보내기
        // 생성 완료됐다고 ok 결과 프론트로 보내기
        try{
            return null;

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/getFile")
    public ResponseEntity<?> getDataSetFile(@RequestParam("file") MultipartFile file) throws IOException {
        try{
            // 생성된 파일 받기
            // z3 실행
            String path ="/Z3 Solver/UsingSolver9.py";
            return null;
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/download")
    public void downloadFiles(HttpServletResponse response) throws IOException{
        // 테스트셋 다운로드
        File directory = new File(FilePath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".xlsx")) {
                    downloadFile(response, file);
                }
            }
        }

    }

    private void downloadFile(HttpServletResponse response, File file) throws IOException {
        // 파일 다운로드 설정
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        // 파일 스트림 읽기
        FileInputStream fis = new FileInputStream(file);
        OutputStream os = response.getOutputStream();

        // 파일 전송
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }

        // 스트림 닫기
        fis.close();
        os.flush();
        os.close();
    }

//    @PostMapping("/result"){
//        public ResponseEntity<?> getDataSetResult(@RequestParam null){
//          // z3 결과 받기
//        }
//    }
}
