package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import graduationProject.graduation_judge.DAO.InfoLecture;
import graduationProject.graduation_judge.DAO.QInfoLecture;
import graduationProject.graduation_judge.DAO.QUserSelectList;
import graduationProject.graduation_judge.DTO.Lecture.InfoLectureDTO;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
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
    public List<String> getUserSelectLectureNicknameList(String user_id) {
        return queryFactory
                .select(infoLecture.lectureNick)
                .from(infoLecture)
                .join(userSelectList)
                .on(infoLecture.classNumber.eq(userSelectList.classNum))
                .fetch();
    }

    @Override
    public List<InfoLectureDTO> getUserSelectLectureInfoList(String user_id) {
        List<InfoLectureDTO> infoLectureDTOList = new ArrayList<>();

        List<InfoLecture> infoLectureList = queryFactory
                .select(infoLecture)
                .from(infoLecture)
                .join(userSelectList)
                .on(infoLecture.classNumber.eq(userSelectList.classNum))
                .fetch();

        Iterator<InfoLecture> iterator = infoLectureList.iterator();
        while (iterator.hasNext()) {
            InfoLecture info_lecture = iterator.next();
            InfoLectureDTO infoLectureDTO = new InfoLectureDTO(
                    info_lecture.getLectureNick(),
                    info_lecture.getCurriculum(),
                    info_lecture.getClassArea(),
                    info_lecture.getClassCredit(),
                    info_lecture.getClassNumber()
                    );
            infoLectureDTOList.add(infoLectureDTO);
        }

        return infoLectureDTOList;
    }

    @Override
    public Long getUserSelectLectureAmount(String user_id) {
        return queryFactory
                .select(userSelectList.count())
                .from(userSelectList)
                .fetchFirst();
    }
}
