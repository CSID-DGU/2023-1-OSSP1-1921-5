package graduationProject.graduation_judge.domain.Grade.repository;

import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DAO.identifier.UserSelectListPK;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<UserSelectList, UserSelectListPK> {
    boolean existsByMemberId(String memberId);
    void deleteAllByMemberId(String memberId);
    
    List<UserSelectList> findAllByMemberId(String memberId);

    List<UserSelectList> findAllByMemberIdAndTermNum(String memberId, String termNum);

    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM UserSelectList u) THEN true ELSE false END")
    boolean existsAnyUserSelectList();
}
