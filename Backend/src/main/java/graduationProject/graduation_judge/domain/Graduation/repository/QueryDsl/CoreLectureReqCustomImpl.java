package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import graduationProject.graduation_judge.DAO.QCoreLectureRequirement;
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
    public List<String> getLectureList(Integer enrollment, Major_curriculum course, String category) {
        return  queryFactory
                .select(coreLectureRequirement.id.lectureName)
                .from(coreLectureRequirement)
                .where(
                        coreLectureRequirement.id.enrollmentYear.eq(enrollment)
                                .and(coreLectureRequirement.id.course.eq(course))
                                .and(coreLectureRequirement.category.eq(category))
                )
                .fetch();
    }
}
