package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import graduationProject.graduation_judge.DAO.QInfoLecture;
import graduationProject.graduation_judge.DAO.QUserSelectList;

import javax.persistence.EntityManager;
import java.util.List;

public class InfoLectureRepositoryCustomImpl implements InfoLectureRepositoryCustom {

    //QueryDSL 작성에 필요한 Q클래스
    QInfoLecture infoLecture = QInfoLecture.infoLecture;
    QUserSelectList userSelectList = QUserSelectList.userSelectList;

    private final JPAQueryFactory queryFactory;

    //생성자
    public InfoLectureRepositoryCustomImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<String> getUserSelectLectureInfoList(String user_id) {
        return queryFactory
                .select(infoLecture.lectureNick)
                .from(infoLecture)
                .join(userSelectList)
                .on(infoLecture.classNumber.eq(userSelectList.classNum))
                .fetch();
    }

    @Override
    public Long getUserSelectLectureAmount(String user_id) {
        return queryFactory
                .select(userSelectList.count())
                .from(userSelectList)
                .fetchFirst();
    }
}
