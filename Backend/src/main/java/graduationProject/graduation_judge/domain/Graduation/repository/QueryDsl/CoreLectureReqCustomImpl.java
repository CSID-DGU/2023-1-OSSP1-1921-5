package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import graduationProject.graduation_judge.DAO.CoreLectureRequirement;
import graduationProject.graduation_judge.DAO.QCoreLectureRequirement;
import graduationProject.graduation_judge.DAO.identifier.CoreLectureRequirementPK;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class CoreLectureReqCustomImpl implements CoreLectureReqCustom{

    private final JPAQueryFactory queryFactory;

    //QueryDSL 작성에 필요한 Q클래스
    QCoreLectureRequirement coreLectureRequirement = QCoreLectureRequirement.coreLectureRequirement;


    //생성자
    public CoreLectureReqCustomImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CoreLectureRequirement> getLectureList(Integer enrollment, Major_curriculum course, String category) {
        return  queryFactory
                .select(coreLectureRequirement)
                .from(coreLectureRequirement)
                .where(
                        coreLectureRequirement.id.enrollmentYear.eq(enrollment)
                                .and(coreLectureRequirement.id.course.eq(String.valueOf(course)))
                                .and(coreLectureRequirement.category.eq(category))
                )
                .fetch();
    }


    @Override
    public void insertCoreLecture(Integer enrollment,
                                  Major_curriculum course,
                                  String category,
                                  String lectureName,
                                  String lecutreNumber
    ) {
        CoreLectureRequirementPK qid = new CoreLectureRequirementPK(enrollment, course, lectureName, lecutreNumber);
        queryFactory.insert(coreLectureRequirement)
                .set(coreLectureRequirement.category, category)
                .set(coreLectureRequirement.id, qid)
                .execute();
    }


}
