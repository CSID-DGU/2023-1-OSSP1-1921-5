package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import graduationProject.graduation_judge.DAO.QDesignLecture;
import graduationProject.graduation_judge.DAO.QEnglishLecture;
import graduationProject.graduation_judge.DAO.QUserSelectList;

import javax.persistence.EntityManager;

public class DesignRepositoryCustomImpl implements DesignRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    //QueryDSL 작성에 필요한 Q클래스
    QUserSelectList userSelectList = QUserSelectList.userSelectList;

    QDesignLecture designLecture = QDesignLecture.designLecture;

    //생성자
    public DesignRepositoryCustomImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public long countDesignClassTaken(String user_id) {
        return queryFactory
                .select(userSelectList.count())
                .from(userSelectList)
                .join(designLecture)
                .on(userSelectList.termNum.eq(designLecture.termNum)
                        .and(userSelectList.classNum.eq(designLecture.classNum)))
                .fetchFirst();
    }
}
