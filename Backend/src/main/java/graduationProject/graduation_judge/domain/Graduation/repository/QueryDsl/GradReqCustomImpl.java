package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import graduationProject.graduation_judge.DAO.QGraduationRequirement;
import graduationProject.graduation_judge.DAO.QInfoLecture;
import graduationProject.graduation_judge.DAO.QUserSelectList;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;

import javax.persistence.EntityManager;
import java.util.List;

public class GradReqCustomImpl implements GradReqCustom{

    //QueryDSL 작성에 필요한 Q클래스
    QInfoLecture infoLecture = QInfoLecture.infoLecture;
    QUserSelectList userSelectList = QUserSelectList.userSelectList;

    QGraduationRequirement graduationRequirement = QGraduationRequirement.graduationRequirement;

    private final JPAQueryFactory queryFactory;

    //생성자
    public GradReqCustomImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void insertGraduationReq(Integer registeredSemesters,
                                            Integer englishScore,
                                            Integer totalEarnedCredit,
                                            Float gpa,
                                            Integer englishClass,
                                            Integer bsmCredit,
                                            Integer commonEducationCredit,
                                            Integer generalEducationCredit,
                                            Integer majorCredit,
                                            GraduationRequirementPK id) {
        queryFactory.insert(graduationRequirement)
                .set(graduationRequirement.registeredSemesters, registeredSemesters)
                .set(graduationRequirement.englishScore, englishScore)
                .set(graduationRequirement.totalEarnedCredit, totalEarnedCredit)
                .set(graduationRequirement.gpa, gpa)
                .set(graduationRequirement.englishClass, englishClass)
                .set(graduationRequirement.bsmCredit, bsmCredit)
                .set(graduationRequirement.commonEducationCredit, commonEducationCredit)
                .set(graduationRequirement.generalEducationCredit, generalEducationCredit)
                .set(graduationRequirement.majorCredit, majorCredit)
                .set(graduationRequirement.id, id)
                .execute();
    }
}
