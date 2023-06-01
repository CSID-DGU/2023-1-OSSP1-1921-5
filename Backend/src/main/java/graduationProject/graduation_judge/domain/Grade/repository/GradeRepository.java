package graduationProject.graduation_judge.domain.Grade.repository;

import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DAO.identifier.UserSelectListPK;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<UserSelectList, UserSelectListPK> {
    boolean existsByMemberId(String memberId);
    void deleteAllByMemberId(String memberId);
    List<String> findDistinctTermNumByMemberId(String memberId);
    int countAllByMemberId(String memberId);
    @EntityGraph(attributePaths = { "entireLecture.infoLecture" })
    List<UserSelectList> findAllByMemberId(String userId);
}