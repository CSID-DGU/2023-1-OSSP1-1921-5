package graduation_spring_test.demo.domain.Member;

import graduation_spring_test.demo.global.common_unit.English_level;
import graduation_spring_test.demo.global.common_unit.Major_curriculum;
import lombok.Data;

@Data
public class Member {

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

    private Integer enroll_year;
    private Integer completed_semesters;
    private Major_curriculum major_curriculum;
    private English_level eng_level;
    private Integer TOEIC_score;

}