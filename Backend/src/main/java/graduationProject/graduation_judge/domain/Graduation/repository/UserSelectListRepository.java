package graduationProject.graduation_judge.domain.Graduation.repository;

import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DAO.identifier.UserSelectListPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserSelectListRepository extends
        JpaRepository<UserSelectList, UserSelectListPK>,
        EnglishRepositoryCustom,
        InfoLectureRepositoryCustom
{

}