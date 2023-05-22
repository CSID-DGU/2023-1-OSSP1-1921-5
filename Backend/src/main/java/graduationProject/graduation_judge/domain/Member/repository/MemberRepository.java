package graduationProject.graduation_judge.domain.Member.repository;

import graduationProject.graduation_judge.DAO.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(String id);
}
