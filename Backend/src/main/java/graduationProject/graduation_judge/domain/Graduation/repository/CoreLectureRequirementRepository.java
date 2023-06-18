package graduationProject.graduation_judge.domain.Graduation.repository;

import graduationProject.graduation_judge.DAO.CoreLectureRequirement;
import graduationProject.graduation_judge.DAO.identifier.CoreLectureRequirementPK;
import graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl.CoreLectureReqCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CoreLectureRequirementRepository extends JpaRepository<CoreLectureRequirement, CoreLectureRequirementPK>, CoreLectureReqCustom {
//    @Query("select c from CoreLectureRequirement c where c.id.lectureNumber = ?1")
//    CoreLectureRequirement findById_LectureNumber(String lectureNumber);
////    CoreLectureRequirement findById_LectureNumber(String lectureNumber);
////    CoreLectureRequirement findById_LectureNumber(Integer lectureNumber);



    @Transactional
    @Modifying
    @Query("update CoreLectureRequirement c set c.cardinality = ?1, c.maxNum = ?2 where c.id = ?3")
    int updateCardinalityAndMax_numById(String cardinality, int max_num, CoreLectureRequirementPK id);


//    CoreLectureRequirement findById_Lecture_number(String lecture_number);

    @Override
    Optional<CoreLectureRequirement> findById(CoreLectureRequirementPK coreLectureRequirementPK);
}