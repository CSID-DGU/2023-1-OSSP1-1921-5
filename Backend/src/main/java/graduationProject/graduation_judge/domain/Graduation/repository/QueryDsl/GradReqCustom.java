package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;

public interface GradReqCustom {

    public void insertGraduationReq(Integer registeredSemesters,
                                    Integer englishScore,
                                    Integer totalEarnedCredit,
                                    Float gpa,
                                    Integer englishClass,
                                    Integer bsmCredit,
                                    Integer commonEducationCredit,
                                    Integer generalEducationCredit,
                                    Integer majorCredit,
                                    GraduationRequirementPK id);
}
