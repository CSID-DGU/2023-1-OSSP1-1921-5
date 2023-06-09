package graduationProject.graduation_judge.domain.Graduation.repository;

import graduationProject.graduation_judge.DAO.GraduationRequirement;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface GraduationRequirementRepository extends JpaRepository<GraduationRequirement, GraduationRequirementPK> {
    //특정 학번의 졸업 요건 조회
    @Override
    Optional<GraduationRequirement> findById(GraduationRequirementPK graduationRequirementPK);


}