package graduationProject.graduation_judge.domain.Graduation.repository;

import graduationProject.graduation_judge.DAO.CoreLectureRequirement;
import graduationProject.graduation_judge.DAO.identifier.CoreLectureRequirementPK;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoreLectureRequirementRepository extends JpaRepository<CoreLectureRequirement, CoreLectureRequirementPK>, CoreLectureReqCustom {
}