package graduationProject.graduation_judge.domain.Graduation.service;

import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import graduationProject.graduation_judge.DTO.Graduation.CoreLectureParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationEligibilityParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationRequirementCond;
import graduationProject.graduation_judge.DTO.ScoreStatDTO;
import graduationProject.graduation_judge.domain.Graduation.repository.CoreLectureRequirementRepository;
import graduationProject.graduation_judge.domain.Graduation.repository.GraduationRequirementRepository;
import graduationProject.graduation_judge.domain.Graduation.repository.UserInfoRepository;
import graduationProject.graduation_judge.domain.Graduation.repository.UserSelectListRepository;
import graduationProject.graduation_judge.domain.Lecture.repository.EnglishLectureRepository;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GraduationServiceImpl implements GraduationService{
    private final UserInfoRepository userInfoRepository;
    private final GraduationRequirementRepository gradReqRepository;
    private final ScoreStatRepository scoreStatRepository;
    private final EnglishLectureRepository englishLectureRepository;

    private final UserSelectListRepository userSelectListRepository;

    private  final CoreLectureRequirementRepository coreLectureRequirementRepository;

    public GraduationServiceImpl(GraduationRequirementRepository gradReqRepository,
                                 UserInfoRepository userInfoRepository, ScoreStatRepository scoreStatRepository, EnglishLectureRepository englishLectureRepository, UserSelectListRepository userSelectListRepository, CoreLectureRequirementRepository coreLectureRequirementRepository) {
        this.gradReqRepository = gradReqRepository;
        this.userInfoRepository = userInfoRepository;
        this.scoreStatRepository = scoreStatRepository;
        this.englishLectureRepository = englishLectureRepository;
        this.userSelectListRepository = userSelectListRepository;
        this.coreLectureRequirementRepository = coreLectureRequirementRepository;
    }
    /**
     * 특정 "학번"과 "커리큘럼"이 적용되는 졸업요건을 조회
     * @param enrollmentYear - 학번(입학년도), must not be null.
     * @param course - 커리큘럼(심화 or 일반), must not be null.
     * @return: requirementCond - 졸업 조건, could be null.
     */
    @Override
    public Optional<GraduationRequirementCond> getGraduationRequirementCond(int enrollmentYear, Major_curriculum course) {
        //조회 결과 해당하는 학번이 없으면 null 일수도 있기 때문
        Optional<GraduationRequirementCond> requirementCond = null;

        try {
            GraduationRequirementPK reqID = GraduationRequirementPK.builder()
                    .enrollmentYear(enrollmentYear)
                    .course(course)
                    .build();

            //졸업 요건 조회
            requirementCond = Optional.of(gradReqRepository.findById(reqID).get().toDTO());

        } catch (Exception e) {
            //처리해야하는 예외 리스트
            //"특정 학번-커리큘럼" 졸업요건 null
            log.warn("GrdService - getGraduationRequirementCond error detected: " + e.getMessage());
        } finally {
            return requirementCond;
        }
    }

    /**
     * "특정 사용자"가 졸업 자격이 되는지 결과 반환
     * @param user_email - 유저 이메일, must not be null.
     * @return GraduationEligibilityParam - 졸업 자격 검증 결과(DTO)
     */
    @Override
    public GraduationEligibilityParam getGraduationEligibilityParam(String user_email) {
        Major_curriculum course = null;
        int enrollmentYear = -1;
        English_level engLevel = null;
        int semester = 0;
        int toeic_score = 0;
        int total_earned_credit = 0;
        int total_taken_subject = 0;
        float GPA = 0;
        int english_class = 0;


        try {
            //user 조회
            UserInfo userInfo = userInfoRepository.findByUserid(user_email).get();

            //학번과 커리큘럼 추출
            enrollmentYear = userInfo.getStudent_number();
            course = userInfo.getCourse();

            //영어레벨 추출
            engLevel = userInfo.getEnglishGrade();

            //졸업 조건 조회
            GraduationRequirementCond condition = getGraduationRequirementCond(enrollmentYear, course).get();

            //user 등록 학기 조회
            semester = userInfo.getSemester();

            //user 토익 점수 조회
            toeic_score = userInfo.getToeicScore();


            ////위의 user 기본 정보를 이용하여 DB에서 조회 ////

            total_taken_subject = Math.toIntExact(userSelectListRepository.getUserSelectLectureAmount(user_email));

            //user 총 이수 학점 조회
            // 조회(filter(where), Projection(select), aggregation(group by))만 DB layer 에서 처리,
            // 계산은 애플리케이션 layer 에서 처리
            List<ScoreStatDTO> stat_list = scoreStatRepository.findByMemberId(user_email);

            log.info("stat_list: " + stat_list.size());

            Iterator<ScoreStatDTO> iterator = stat_list.iterator();
            while (iterator.hasNext()) {
                ScoreStatDTO stat = iterator.next();
                total_earned_credit += stat.getCredit();
            }

            log.info("total_earned_credit: " + total_earned_credit);

            //user 평균 학점 조회
            iterator = stat_list.iterator();
            while (iterator.hasNext()) {
                ScoreStatDTO stat = iterator.next();
                float semester_grade = stat.getGrade();
                int semester_credit = stat.getCredit();
                GPA += semester_grade * semester_credit;
            }
            GPA = (float) (Math.round((GPA / total_earned_credit) * 100) / 100.0);

            log.info("GPA: " + GPA);


            //user 수강 영어 강의 수 조회
            english_class = 0;
            english_class = (int) userSelectListRepository.countEnglishClassTaken(user_email);
            log.info("english_class: " + english_class);


        } catch (Exception e) {
            //처리해야하는 예외 리스트
            //user null
            //user 학번, 커리큘럼 null
            log.error("GrdService - getGraduationEligibilityParam error detected: " + e.getMessage());
        } finally {
            GraduationEligibilityParam eligibilityParam = new GraduationEligibilityParam().builder()
                    .Result(total_taken_subject)
                    .StudentNumber(enrollmentYear)
                    .Course(course)
                    .EngLevel(engLevel)
                    .Register(semester)
                    .EngScore(toeic_score)
                    .TotalScore(GPA)
                    .TotalCredit(total_earned_credit)
                    .EngClassCount(english_class)
                    .build();

            log.info("testing Result: " + eligibilityParam.getTotalScore());
            return eligibilityParam;
        }


    }

    /**
     * 사용자의 필수과목 이수 현황 확인
     * @param: user_email - 유저 이메일, must not be null.
     * @return:
     */
    @Override
    public CoreLectureParam checkEssLectureCompletion(String user_email) {

        List<String> major = null;
        List<String> common_edu = null;
        List<String> general_edu = null;
        List<String> user_select = null;

        try {
            //user 조회
            UserInfo userInfo = userInfoRepository.findByUserid(user_email).get();

            //학번과 커리큘럼 추출
            int enrollmentYear = userInfo.getStudent_number();
            Major_curriculum course = userInfo.getCourse();

            //필수과목 리스트 추출
            major = coreLectureRequirementRepository.getLectureList(enrollmentYear, course, "전필");
            common_edu = coreLectureRequirementRepository.getLectureList(enrollmentYear, course, "공교");
            general_edu = coreLectureRequirementRepository.getLectureList(enrollmentYear, course, "기본소양");

            //수강한 과목 리스트 추출
            user_select = userSelectListRepository.getUserSelectLectureInfoList(user_email);

            //전공 과목 체크
            for(int i =0; i < major.size(); i++){
                log.info("major: {}", major.get(i));
                for(int j = 0; j < user_select.size(); j++){
                    if(major.get(i)==user_select.get(j)) {
                        major.remove(i);
                    }
                }
            }

            //공통 교양 체크
            for(int i =0; i < common_edu.size(); i++){
                log.info("common_edu: {}", common_edu.get(i));
                for(int j = 0; j < user_select.size(); j++){
                    if(common_edu.get(i)==user_select.get(j)) {
                        common_edu.remove(i);
                    }
                }
            }

            //기본 소양 체크
            for(int i =0; i < general_edu.size(); i++){
                log.info("general_edu: {}", general_edu.get(i));
                for(int j = 0; j < user_select.size(); j++){
                    if(general_edu.get(i)==user_select.get(j)) {
                        general_edu.remove(i);
                    }
                }
            }
//            for(int i =0; i < user_select.size(); i++){
//                log.info("user_select: {}", user_select.get(i));
//            }

        } catch (Exception e) {
            log.error("GrdService - checkEssLectureCompletion error detected: " + e.getMessage());
        } finally {
            CoreLectureParam coreLectureParam = new CoreLectureParam().builder()
                    .notTakingNC(common_edu)
                    .notTakingBSM(general_edu)
                    .notTakingMJ(major)
                    .build();
            return coreLectureParam;
        }


    }
}
