package graduation_spring_test.demo.domain.Member;

import graduation_spring_test.demo.global.common_unit.English_level;
import graduation_spring_test.demo.global.common_unit.Major_curriculum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name = "id")
    private String id; //아이디
    @Column(name = "password")
    private String password; //비밀번호
    @Column(name = "enroll_year")
    private Integer enroll_year; //입학년도
    @Column(name = "completed_semesters")
    private Integer completed_semesters; //학기
    @Column(name = "major_curriculum")
    private Major_curriculum major_curriculum; //심화 or 일반
    @Column(name = "eng_level")
    private English_level eng_level; //S0, S1..
    @Column(name = "TOEIC_score")
    private Integer TOEIC_score; //토익 점수
    @Column(name = "security_code")
    private String  security_code; //비밀번호 변경시 보안코드

    public Member(String id, String password, Integer enroll_year, Integer completed_semesters, Major_curriculum major_curriculum, English_level eng_level, Integer TOEIC_score, String security_code) {
        this.id = id;
        this.password = password;
        this.enroll_year = enroll_year;
        this.completed_semesters = completed_semesters;
        this.major_curriculum = major_curriculum;
        this.eng_level = eng_level;
        this.TOEIC_score = TOEIC_score;
        this.security_code = security_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnroll_year() {
        return enroll_year;
    }

    public void setEnroll_year(Integer enroll_year) {
        this.enroll_year = enroll_year;
    }

    public Integer getCompleted_semesters() {
        return completed_semesters;
    }

    public void setCompleted_semesters(Integer completed_semesters) {
        this.completed_semesters = completed_semesters;
    }

    public Major_curriculum getMajor_curriculum() {
        return major_curriculum;
    }

    public void setMajor_curriculum(Major_curriculum major_curriculum) {
        this.major_curriculum = major_curriculum;
    }

    public English_level getEng_level() {
        return eng_level;
    }

    public void setEng_level(English_level eng_level) {
        this.eng_level = eng_level;
    }

    public Integer getTOEIC_score() {
        return TOEIC_score;
    }

    public void setTOEIC_score(Integer TOEIC_score) {
        this.TOEIC_score = TOEIC_score;
    }

    public String getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(String security_code) {
        this.security_code = security_code;
    }
}