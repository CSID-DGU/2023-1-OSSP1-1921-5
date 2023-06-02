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
    int countAllByMemberId(String memberId);
    
    List<UserSelectList> findAllByMemberId(String memberId); //queryDSL로 고치기

    @EntityGraph(attributePaths = { "entireLecture.infoLecture" })
    List<UserSelectList> findAllByMemberIdAndTermNum(String memberId, String termNum);
}