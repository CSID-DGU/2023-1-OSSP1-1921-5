package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import graduationProject.graduation_judge.DAO.QEnglishLecture;
import graduationProject.graduation_judge.DAO.QUserSelectList;

import javax.persistence.EntityManager;

public class EnglishRepositoryCustomImpl implements EnglishRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    //QueryDSL 작성에 필요한 Q클래스
    QUserSelectList userSelectList = QUserSelectList.userSelectList;
    QEnglishLecture englishLecture = QEnglishLecture.englishLecture;

    //생성자
    public EnglishRepositoryCustomImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public long countEnglishClassTaken(String user_id) {
        return queryFactory
                .select(userSelectList.count())
                .from(userSelectList)
                .join(englishLecture)
                .on(userSelectList.termNum.eq(englishLecture.termNum)
                        .and(userSelectList.classNum.eq(englishLecture.classNum)))
                .fetchFirst();
    }
}
