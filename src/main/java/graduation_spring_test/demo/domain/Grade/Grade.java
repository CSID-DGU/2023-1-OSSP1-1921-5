package graduation_spring_test.demo.domain.Grade;


import lombok.Data;

@Data
public  class Grade {
    private String memberId;
    private String termNum; //이수년도_학기
    private String classNum; //학수번호
    private String score; //성적 점수
}
