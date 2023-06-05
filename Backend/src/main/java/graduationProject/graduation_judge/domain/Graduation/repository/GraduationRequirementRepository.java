package graduationProject.graduation_judge.domain.Graduation.repository;

import graduationProject.graduation_judge.DAO.GraduationRequirement;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface GraduationRequirementRepository extends JpaRepository<GraduationRequirement, GraduationRequirementPK> {
    @Query("select g from GraduationRequirement g where g.gpa >= ?1 order by g.gpa")
    List<GraduationRequirement> findByGpaGreaterThanEqualOrderByGpaAsc(@Nullable Float gpa);
}