package graduationProject.graduation_judge.DTO.Graduation;

import com.fasterxml.jackson.annotation.JsonProperty;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
public class GraduationEligibilityParam {
    /**
     * Register: 1)등록학기
     * EngScore: 2)외국어 성적
     * TotalCredit: 3)취득학점
     * TotalScore: 4)성적평점
     * EngClassCount: 5)영어강의
     *
     * StudentNumber: 학번 (입학년도)
     * Course: 커리큘럼(심화/일반)
     * EngLevel: 영어등급(s0..s4)
     * Result: 총 이수 과목 수
     *
     * MajorCredit: 이수한 전공 학점
     * SpecialMajorCredit: 이수한 전공 & 전문 학점
     * CommonClassCredit: 이수한 공통교양학점
     * GibonSoyangCredit: 이수한 기본소양학점 //프론트와 협의 후 네이밍 변경해야함
     * BSMCredit: 이수한 bsm 학점
     * BSMMathCredit: 이수한 bsm_수학 학점
     * BSMSciCredit: 이수한 bsm_과학 학점
     */
    @JsonProperty("Result")
    private boolean Result;

    @JsonProperty("Course")
    private Major_curriculum Course;

    @JsonProperty("StudentNumber")
    private int StudentNumber;

    @JsonProperty("EngLevel")
    private English_level EngLevel;


    ////////////////////////////////////////////////////////////////

    @JsonProperty("TotalScore")
    private float TotalScore;
    @JsonProperty("Register")
    private int Register;

    @JsonProperty("TotalCredit")
    private int TotalCredit;

    @JsonProperty("EngClassCount")
    private int EngClassCount;

    @JsonProperty("EngScore")
    private int EngScore;

    @JsonProperty("CommonClassCredit")
    private int CommonClassCredit;

    @JsonProperty("GibonsoyangCredit")
    private int GibonSoyangCredit;

    @JsonProperty("BSMCredit")
    private int BSMCredit;

    @JsonProperty("BSMMathCredit")
    private int BSMMathCredit;
    @JsonProperty("BSMSciCredit")
    private int BSMSciCredit;

    @JsonProperty("MajorCredit")
    private int MajorCredit;

    @JsonProperty("SpecialMajorCredit")
    private int SpecialMajorCredit;

    @JsonProperty("Eligibility_Result_List")
    HashMap<String, Eligibility_Result_Unit> Eligibility_Result_List = new HashMap<>();


    @Builder
    public GraduationEligibilityParam(
            boolean Result,
            Major_curriculum Course,
            int StudentNumber,
            English_level EngLevel,
            int Register,
            int EngScore,
            int TotalCredit,
            int CommonClassCredit,
            int GibonSoyangCredit,
            int BSMCredit,
            int BSMMathCredit,
            int BSMSciCredit,
            int MajorCredit,
            int SpecialMajorCredit,
            int EngClassCount,
            float TotalScore,
            HashMap<String, Eligibility_Result_Unit> Eligibility_Result_List
    ) {
        this.Result = Result;
        this.Course = Course;
        this.StudentNumber = StudentNumber;
        this.EngLevel = EngLevel;
        this.Register = Register;
        this.EngScore = EngScore;
        this.TotalCredit = TotalCredit;
        this.CommonClassCredit = CommonClassCredit;
        this.GibonSoyangCredit = GibonSoyangCredit;
        this.BSMCredit = BSMCredit;
        this.BSMMathCredit = BSMMathCredit;
        this.BSMSciCredit = BSMSciCredit;
        this.MajorCredit = MajorCredit;
        this.SpecialMajorCredit = SpecialMajorCredit;
        this.TotalScore = TotalScore;
        this.EngClassCount = EngClassCount;
        this.Eligibility_Result_List = Eligibility_Result_List;
    };

    public GraduationEligibilityParam() {}
}