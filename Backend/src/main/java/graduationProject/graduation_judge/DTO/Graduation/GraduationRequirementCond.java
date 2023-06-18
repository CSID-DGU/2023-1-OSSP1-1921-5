package graduationProject.graduation_judge.DTO.Graduation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <h3>학번별 적용 졸업요건 DTO</h3>
 * <p>
 * Registered_semesters: 등록학기 <br/>
 * English_score: 외국어 성적 <br/>
 * Total_earned_credit: 취득학점 <br/>
 * GPA: 성적평점 (Grade Point Average) <br/>
 * English_class: 이수 영어강의 수 <br/>
 * </p> <br/>
 * <p>
 * BSM_credit: BSM 학점 <br/>
 * BSM_math_credit: BSM-수학 학점 <br/>
 * BSM_sci_credit: BSM-과학 학점 <br/>
 * Common_education_credit: 공통교양학점 <br/>
 * Course: 전공 커리큘럼 (심화/일반) <br/>
 * English_level: 영어등급(S0..S4) <br/>
 * General_education_credit: 기본소양학점 <br/>
 * Major_credit: 전공 학점 <br/>
 * Result: 이수한 총 과목 수 <br/>
 * SpecialMajor_credit: 이수한 전공 & 전문 학점 <br/>
 * Student_number: 학번 <br/>
 * </p>
 */
@Getter
@Setter
public class GraduationRequirementCond {

    private int Registered_semesters;
    private int Toeic_score;
    private int Total_earned_credit;
    private float GPA;
    private int English_class;

    private int CommonClassCredit;

    private int GibonSoyangCredit;

    private int BSMCredit;

    private int BSMMathCredit;

    private int BSMSciCredit;

    private int MajorCredit;

    private int SpecialMajorCredit;

    private int leadership_credit;

    private int seminar_credit;



    @Builder
    public GraduationRequirementCond(
            int Registered_semesters,
            int Toeic_score,
            int Total_earned_credit,
            float GPA,
            int English_class,
            int CommonClassCredit,
            int GibonSoyangCredit,
            int BSMCredit,
            int BSMMathCredit,
            int BSMSciCredit,
            int MajorCredit,
            int SpecialMajorCredit,
            int leadership_credit,
            int seminar_credit
    ) {
        this.Registered_semesters = Registered_semesters;
        this.Toeic_score = Toeic_score;
        this.Total_earned_credit = Total_earned_credit;
        this.GPA = GPA;
        this.English_class = English_class;
        this.CommonClassCredit = CommonClassCredit;
        this.GibonSoyangCredit = GibonSoyangCredit;
        this.BSMCredit = BSMCredit;
        this.BSMMathCredit = BSMMathCredit;
        this.BSMSciCredit = BSMSciCredit;
        this.MajorCredit = MajorCredit;
        this.SpecialMajorCredit = SpecialMajorCredit;
        this.leadership_credit = leadership_credit;
        this.seminar_credit = seminar_credit;
    }

}
