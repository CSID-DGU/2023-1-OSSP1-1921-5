package graduationProject.graduation_judge.domain.DataSet.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dataset")
public class DataSetController {
    @PostMapping("/create")
    public ResponseEntity<?> createDataSet(@RequestBody Map<String, Object> request) throws IOException {

        try {
            // JSON 문자열을 자바 객체로 변환
            Map<String, Object> subjects = (Map<String, Object>) request.get("subjects");
            for (Map.Entry<String, Object> entry : subjects.entrySet()) {
                List<Object> subjectValues = (List<Object>) entry.getValue();
                for (int i = 0; i < subjectValues.size(); i++) {
                    if (subjectValues.get(i) instanceof String) {
                        String value = (String) subjectValues.get(i);
                        if (value.startsWith("\"") && value.endsWith("\"")) {
                            subjectValues.set(i, value.substring(1, value.length() - 1));
                        }
                    }
                }
            }

            // 기존 dataset 파일 삭제
            File directory = new File("testDataSet/data");
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && (file.getName().endsWith(".xls") || file.getName().endsWith(".xlsx"))) {
                        if (file.delete()) {
                            System.out.println("delete success: " + file.getName());
                        } else {
                            System.out.println("delete fail: " + file.getName());
                        }
                    }
                }
            }

            System.out.println("request = " + request);
            // 파이썬 서버 URL
            String pythonServerUrl = "http://localhost:5000/dataset";

            // RestTemplate 생성
            RestTemplate restTemplate = new RestTemplate();

            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // HTTP 요청 바디 설정
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(request, headers);

            // POST 요청 보내기
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(pythonServerUrl, requestEntity, String.class);

            // 응답 처리
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String response = responseEntity.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(response);
                String message = jsonResponse.get("message").asText();

                if (message.equals("complete")) {
                    return ResponseEntity.ok().body("데이터셋 생성 완료");
                } else {
                    System.out.println("response error");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("응답 처리 오류");
                }
            } else {
                System.out.println("server error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파이썬 서버 오류");
            }
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러: " + e.getMessage());
        }
    }

    @GetMapping("/download")
    public void downloadFiles(HttpServletResponse response) throws IOException{
        // 테스트셋 다운로드 + z3 결과 다운로드
        String testFilePath = "testDataSet/data";
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

    @PostMapping("/createtest")
    public ResponseEntity<?> testPath(@RequestBody Map<String, Object> request)throws IOException{

            String createDataPython2 = "testDataSet/createUsingSolver.py"; // 실행할 파이썬 스크립트의 경로
            // 파일이 존재하는지 확인
            File file = new File(createDataPython2);
            System.out.println("file.getPath() = " + file.getPath());
            if (file.exists()) {
                System.out.println("file yes");
            } else {
                System.out.println("file no");
            }

        return ResponseEntity.ok().body("파일 경로 확인 완료");
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