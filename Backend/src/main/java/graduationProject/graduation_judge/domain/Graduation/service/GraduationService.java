package graduationProject.graduation_judge.domain.Graduation.service;

import graduationProject.graduation_judge.DTO.EssLectureResult;
import graduationProject.graduation_judge.DTO.GraduationResultList;
import org.springframework.stereotype.Service;

@Service
public interface GraduationService {
    public GraduationResultList checkGraduationEligibility();

    public EssLectureResult checkEssLectureCompletion();
}