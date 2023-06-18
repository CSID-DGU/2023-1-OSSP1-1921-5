package graduationProject.graduation_judge.domain.Graduation.repository;

import graduationProject.graduation_judge.DAO.GraduationRequirement;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl.GradReqCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GraduationRequirementRepository extends JpaRepository<GraduationRequirement, GraduationRequirementPK>, GradReqCustom {
    //특정 학번의 졸업 요건 조회
    @Override
    Optional<GraduationRequirement> findById(GraduationRequirementPK graduationRequirementPK);

    @Override
    boolean existsById(GraduationRequirementPK graduationRequirementPK);

    @Transactional
    @Modifying
    @Query("update GraduationRequirement g set g.englishScore = ?1, g.totalEarnedCredit = ?2, g.gpa = ?3, g.englishClass = ?4, g.bsmCredit = ?5, g.commonEducationCredit = ?6, g.generalEducationCredit = ?7, g.majorCredit = ?8 " +
            "where g.id = ?9")
    void updateRequirement(
            Integer englishScore,
            Integer totalEarnedCredit,
            Float gpa,
            Integer englishClass,
            Integer bsmCredit,
            Integer commonEducationCredit,
            Integer generalEducationCredit,
            Integer majorCredit,
            GraduationRequirementPK id);




//    @Modifying
//    @Query("insert into GraduationRequirement (registeredSemesters, englishScore, totalEarnedCredit, gpa, englishClass, bsmCredit, bsmMathCredit, bsmSciCredit, commonEducationCredit, generalEducationCredit, majorCredit, id) " +
//            "values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12)")
//    void insertRequirement(
//            Integer registeredSemesters,
//            Integer englishScore,
//            Integer totalEarnedCredit,
//            Float gpa,
//            Integer englishClass,
//            Integer bsmCredit,
//            Integer bsmMathCredit,
//            Integer bsmSciCredit,
//            Integer commonEducationCredit,
//            Integer generalEducationCredit,
//            Integer majorCredit,
//            GraduationRequirementPK id
//    );






}