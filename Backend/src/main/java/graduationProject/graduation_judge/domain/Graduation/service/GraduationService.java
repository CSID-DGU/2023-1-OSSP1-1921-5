package graduationProject.graduation_judge.domain.Graduation.service;

import graduationProject.graduation_judge.DTO.Graduation.CoreLectureCond;
import graduationProject.graduation_judge.DTO.Graduation.GraduationEligibilityParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationRequirementCond;
import org.springframework.stereotype.Service;

/**
 * 0. 요청 받음 -> (사용자 아이디)
 * 1. 사용자 아이디 -> 해당하는 졸업요건 튜플 조회
 * 2. 사용자 아이디 -> 사용자의 성적 조회
 * 3. 2.에서 조회한 성적을 기반으로 졸업요건 일치 확인
 * 4. 계산된 결과 리턴
 */

@Service
public interface GraduationService {

    /**
     * 사용자의 학번이 적용되는 졸업요건을 조회
     * @param user_email - 유저 이메일, must not be null.
     * @return graduation_requirement - 졸업 요건
     */
    public GraduationRequirementCond getGraduationRequirementCond(String user_email);


    /**
     * 졸업요건을 기준으로 사용자의 만족 여부 반환
     * @param user_email - 유저 이메일, must not be null.
     * @return GraduationEligibilityParam - 졸업 자격 검증 결과(DTO)
     */
    public GraduationEligibilityParam getGraduationEligibilityParam(String user_email);

    /**
     *
     * @return CoreLectureParam - 필수강의 수강 결과(DTO)
     */
    public CoreLectureCond checkEssLectureCompletion();
}