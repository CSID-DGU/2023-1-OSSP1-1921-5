package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *  * <h3>학번별 적용 졸업요건 DTO</h3>
 *  * <p>
 *  * Registered_semesters: 등록학기 <br/>
 *  * English_score: 외국어 성적 <br/>
 *  * Total_earned_credit: 취득학점 <br/>
 *  * GPA: 성적평점 (Grade Point Average) <br/>
 *  * English_class: 이수 영어강의 수 <br/>
 *  * </p> <br/>
 *  * <p>
 *  * BSM_Credit: BSM 학점 <br/>
 *  * BSMMath_Credit: BSM-수학 학점 <br/>
 *  * BSMSci_Credit: BSM-과학 학점 <br/>
 *  * Common_education_credit: 공통교양학점 <br/>
 *  * Course: 전공 커리큘럼 (심화/일반) <br/>
 *  * English_level: 영어등급(S0..S4) <br/>
 *  * General_education_credit: 기본소양학점 <br/>
 *  * Major_credit: 전공 학점 <br/>
 *  * Result: 이수한 총 과목 수 <br/>
 *  * SpecialMajor_credit: 이수한 전공 & 전문 학점 <br/>
 *  * Student_number: 학번 <br/>
 *  * </p>
 */

@Getter
@Setter
@Entity
@Table(name = "graduation_requirement")
public class GraduationRequirement {
    @EmbeddedId
    private GraduationRequirementPK id;

    @Column(name = "registered_semesters")
    private Integer registeredSemesters;

    @Column(name = "english_score")
    private Integer englishScore;

    @Column(name = "total_earned_credit")
    private Integer totalEarnedCredit;

    @Column(name = "GPA")
    private Float gpa;

    @Column(name = "english_class")
    private Integer englishClass;

    @Column(name = "BSM_credit")
    private Integer bsmCredit;

    @Column(name = "BSM_math_credit")
    private Integer bsmMathCredit;

    @Column(name = "BSM_sci_credit")
    private Integer bsmSciCredit;

    @Column(name = "Common_education_credit")
    private Integer commonEducationCredit;

    @Column(name = "General_education_credit")
    private Integer generalEducationCredit;

    @Column(name = "Major_credit")
    private Integer majorCredit;

    @Builder
    public GraduationRequirement(
            GraduationRequirementPK id,
            Integer registeredSemesters,
            Integer englishClass,
            Integer totalEarnedCredit,
            Float gpa,
            Integer bsmCredit,
            Integer bsmMathCredit,
            Integer bsmSciCredit,
            Integer commonEducationCredit,
            Integer generalEducationCredit,
            Integer majorCredit) {
        this.id = id;
        this.registeredSemesters = registeredSemesters;
        this.englishClass = englishClass;
        this.totalEarnedCredit = totalEarnedCredit;
        this.gpa = gpa;
        this.bsmCredit = bsmCredit;
        this.bsmMathCredit = bsmMathCredit;
        this.bsmSciCredit = bsmSciCredit;
        this.commonEducationCredit = commonEducationCredit;
        this.generalEducationCredit = generalEducationCredit;
        this.majorCredit = majorCredit;
    }

    public GraduationRequirement() {}
}





