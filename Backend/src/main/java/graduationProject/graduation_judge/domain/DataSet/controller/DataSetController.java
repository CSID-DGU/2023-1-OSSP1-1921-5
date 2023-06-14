package graduationProject.graduation_judge.domain.DataSet.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
@RequestMapping("/dataset")
public class DataSetController {

    @PostMapping("/create")
    public ResponseEntity<?> createDataSet(@RequestBody Map<String, String> request)throws IOException{
        // test data set 명령어 입력 받기 (파라미터로 넘길 인자들)
        String dataNum = request.get("dataNum"); // 만들 data 수
        String admissionYear = request.get("admissionYear"); // 입학년도
        String completeSem = request.get("completeSem"); // 이수학기
        String subjects = request.get("subjects"); // 제약사항 관련 (수정 필요~)

        try{
            // testDataset python 으로 명령어 보내기
            String createDataPython = "/testDataSet/createData.py"; // 실행할 파이썬 스크립트의 경로
            String createDataFunction = ""; // 실행할 함수의 이름

            // 실행할 명령어 (수정 필요~)
            String command = String.format("python %s %s %s %s", createDataPython, createDataFunction, dataNum, admissionYear);

            // 명령어 실행
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // python 처리완료될 때 까지 기다리기
            try {
                int exitCode = process.waitFor();
                System.out.println("TestSet Python script execution completed with exit code: " + exitCode);
            } catch (InterruptedException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            // exitCode 값에 따른 처리 필요?

            // python으로부터 결과 받기 (test set의 저장 경로)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String testFilePath = "";
            while ((line = reader.readLine()) != null) {
                // 결과 읽기
                testFilePath += line;
            }
            
            // test set 저장 경로를 z3에게 보내기
            String z3SolverPython = ""; // z3 python 경로
            String z3Function = ""; // z3 실행할 함수 이름
            command = String.format("python %s %s %s", z3SolverPython, z3Function, testFilePath);

            // 명령어 실행
            processBuilder = new ProcessBuilder(command.split(" "));
            process = processBuilder.start();

            // python 처리완료될 때 까지 기다리기
            try {
                int exitCode = process.waitFor();
                System.out.println("Z3 Python script execution completed with exit code: " + exitCode);
            } catch (InterruptedException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

            // z3로부터 결과 엑셀 파일 저장경로 받기
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line = "";
            String resultFilePath = "";
            while ((line = reader.readLine()) != null) {
                // 결과 읽기
                resultFilePath += line;
            }

            // 생성 완료됐다고 ok 결과 프론트로 보내기
            return ResponseEntity.ok().body("데이터셋 생성 완료");

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/download")
    public void downloadFiles(HttpServletResponse response) throws IOException{
        // 테스트셋 다운로드 + z3 결과 다운로드
        String testFilePath = "../../../../../../../../testDataSet/data";
        String resultFilePath;
        
        File testFiledirectory = new File(testFilePath);
        File[] files = testFiledirectory.listFiles();
        if (files != null) {
            for (File file : files) { // 해당 경로의 모든 엑셀파일을 다운로드
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
}