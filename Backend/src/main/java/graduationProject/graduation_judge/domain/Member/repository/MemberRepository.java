package graduationProject.graduation_judge.domain.Member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(String id);
}
