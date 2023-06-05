package graduationProject.graduation_judge.domain.Graduation.service;

import graduationProject.graduation_judge.DAO.GraduationRequirement;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import graduationProject.graduation_judge.DTO.Graduation.CoreLectureCond;
import graduationProject.graduation_judge.DTO.Graduation.GraduationEligibilityParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationRequirementCond;
import graduationProject.graduation_judge.domain.Graduation.repository.GraduationRequirementRepository;
import graduationProject.graduation_judge.domain.Graduation.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GraduationServiceImpl implements GraduationService{
    private final UserInfoRepository userInfoRepository;
    private final GraduationRequirementRepository gradReqRepository;

    public GraduationServiceImpl(GraduationRequirementRepository gradReqRepository,
                                 UserInfoRepository userInfoRepository) {
        this.gradReqRepository = gradReqRepository;
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * 사용자의 학번이 적용되는 졸업요건을 조회
     * @param: user_email - 유저 이메일, must not be null.
     * @return:
     */
    @Override
    public GraduationRequirementCond getGraduationRequirementCond(String user_email) {
        int student_number = userInfoRepository.findByPincode(user_email).get().getStudent_number();
        log.info("student_id = {}", student_number);
        //test_code
        GraduationRequirementPK grdpk_2019_a = GraduationRequirementPK.builder()
                .enrollmentYear(2019)
                .course("심화")
                .build();

        GraduationRequirement new_grd_req_cond = GraduationRequirement.builder()
                .id(grdpk_2019_a)
                .gpa(3.0F)
                .registeredSemesters(7)
                .build();

        gradReqRepository.save(new_grd_req_cond);

        log.info("test result = {}", gradReqRepository.findByGpaGreaterThanEqualOrderByGpaAsc(2.0F).get(0).getGpa());
        return null;
    }

    /**
     * 졸업요건을 기준으로 사용자의 만족 여부 반환
     * @param user_email - 유저 이메일, must not be null.
     * @return GraduationEligibilityParam - 졸업 자격 검증 결과(DTO)
     */
    @Override
    public GraduationEligibilityParam getGraduationEligibilityParam(String user_email) {
        return null;
    }

    /**
     * 사용자의 학번이 해당되는 졸업요건을 조회
     * @param: userid - 유저 이메일, must not be null.
     * @return:
     */
    @Override
    public CoreLectureCond checkEssLectureCompletion() {
        return null;
    }
}
