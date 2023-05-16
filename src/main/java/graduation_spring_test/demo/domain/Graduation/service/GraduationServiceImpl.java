package graduation_spring_test.demo.domain.Graduation.service;

import graduation_spring_test.demo.DTO.EssLectureResult;
import graduation_spring_test.demo.DTO.GraduationResultList;
import org.springframework.stereotype.Service;

@Service
public class GraduationServiceImpl implements GraduationService{

    @Override
    public GraduationResultList checkGraduationEligibility() {
        return null;
    }

    @Override
    public EssLectureResult checkEssLectureCompletion() {
        return null;
    }
}
