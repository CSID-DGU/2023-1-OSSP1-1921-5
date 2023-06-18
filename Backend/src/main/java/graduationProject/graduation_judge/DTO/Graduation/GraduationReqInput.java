package graduationProject.graduation_judge.DTO.Graduation;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class GraduationReqInput {
        @JsonProperty("이수구분")
        private String category;
        @JsonProperty("학수강좌번호")
        private String lectureNumber;
        @JsonProperty("학점")
        private int credit;
        @JsonProperty("교과목명")
        private String lectureName;
        @JsonProperty("비고")
        private String comment;
        @JsonProperty("전공")
        private int majorCredit;
        @JsonProperty("공통교양")
        private int commonCredit;
        @JsonProperty("기본소양")
        private int generalCredit;
        @JsonProperty("학문기초")
        private int bsmCredit;
        @JsonProperty("설계")
        private int designCredit;
        @JsonProperty("총학점")
        private int totalCredit;
        @JsonProperty("평점평균")
        private float gpa;
        @JsonProperty("외국어시험")
        private int englishExamScore;
        @JsonProperty("영어강의")
        private int englishLecture;
        @JsonProperty("영어(전공)")
        private int englishMajorLecture;

        // 기본 생성자
        public GraduationReqInput() {}
}
