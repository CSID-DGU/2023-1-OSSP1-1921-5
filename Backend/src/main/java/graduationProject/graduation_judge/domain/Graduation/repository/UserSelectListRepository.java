package graduationProject.graduation_judge.domain.Graduation.repository;

import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DAO.identifier.UserSelectListPK;
import graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl.EnglishRepositoryCustom;
import graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl.InfoLectureRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSelectListRepository extends
        JpaRepository<UserSelectList, UserSelectListPK>,
        EnglishRepositoryCustom,
        InfoLectureRepositoryCustom
{

}