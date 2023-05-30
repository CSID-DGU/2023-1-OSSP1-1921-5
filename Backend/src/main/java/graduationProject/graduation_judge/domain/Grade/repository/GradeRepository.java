package graduationProject.graduation_judge.domain.Grade.repository;

import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.domain.Grade.GradeId;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<UserSelectList, GradeId> {
    boolean existsByMemberId(String memberId);
    void deleteAllByMemberId(String memberId);
    @EntityGraph(attributePaths = { "entireLecture.infoLecture" })
    List<UserSelectList> findAllByMemberId(String userId);
}