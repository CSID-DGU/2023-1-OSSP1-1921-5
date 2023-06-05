package graduationProject.graduation_judge.DTO.Graduation;

import graduationProject.graduation_judge.DAO.GraduationRequirement;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import lombok.Builder;
import lombok.Data;

@Data
public class GraduationEligibilityParam {
    /**
     * Register: 1)등록학기
     * EngScore: 2)외국어 성적
     * TotalCredit: 3)취득학점
     * TotalScore: 4)성적평점
     * EngClassCount: 5)영어강의
     * BSMCredit: 이수한 bsm_수학 학점
     * BSMMathCredit: 이수한 bsm_수학 학점
     * BSMSciCredit: 이수한 bsm_과학 학점
     * CommonClassCredit: 이수한 공통교양학점
     * Course: 심화/일반 (UserInfo의 Course 컬럼)
     * EngLevel: 영어등급 (Userinfo의 EnglishGrade 컬럼)
     * GibonSoyangCredit: 이수한 기본소양학점 //프론트와 협의 후 네이밍 변경해야함
     * MajorCredit: 이수한 전공 학점
     * Result: 이수한 총 과목 수
     * SpecialMajorCredit: 이수한 전공 & 전문 학점
     * StudentNumber: 학번 (Userinfo의 StudentNumber 컬럼)
     */
    private int Register;
    private int EngScore;
    private int TotalCredit;
    private float TotalScore;
    private int EngClassCount;

    @Builder
    public GraduationEligibilityParam(
            int Register,
            int EngScore,
            int TotalCredit,
            float TotalScore,
            int EngClassCount
    ) {
        this.Register = Register;
        this.EngScore = EngScore;
        this.TotalCredit = TotalCredit;
        this.TotalScore = TotalScore;
        this.EngClassCount = EngClassCount;
    };

    //testcode
    GraduationRequirementPK grdpk_2019_a = GraduationRequirementPK.builder()
            .enrollmentYear(2019)
            .course("심화")
            .build();
    public GraduationRequirement toEntity() {
        return GraduationRequirement.builder()
                .id(grdpk_2019_a)
                .build();
    }

    public GraduationEligibilityParam() {}
}