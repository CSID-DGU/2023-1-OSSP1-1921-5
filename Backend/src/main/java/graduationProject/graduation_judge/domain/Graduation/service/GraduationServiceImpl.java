package graduationProject.graduation_judge.domain.Graduation.service;

import graduationProject.graduation_judge.DAO.*;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import graduationProject.graduation_judge.DTO.Graduation.CoreLectureParam;
import graduationProject.graduation_judge.DTO.Graduation.EligibilityResultUnit;
import graduationProject.graduation_judge.DTO.Graduation.GraduationEligibilityParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationRequirementCond;
import graduationProject.graduation_judge.DTO.Lecture.InfoLectureDTO;
import graduationProject.graduation_judge.DTO.Stats.ScoreStatDTO;
import graduationProject.graduation_judge.domain.Graduation.repository.*;
import graduationProject.graduation_judge.domain.Lecture.repository.EnglishLectureRepository;
import graduationProject.graduation_judge.domain.Lecture.repository.InfoLectureRepository;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class GraduationServiceImpl implements GraduationService{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final UserInfoRepository userInfoRepository;
    private final GraduationRequirementRepository gradReqRepository;
    private final ScoreStatRepository scoreStatRepository;
    private final EnglishLectureRepository englishLectureRepository;

    private final InfoLectureRepository infoLectureRepository;

    private final UserSelectListRepository userSelectListRepository;


    private  final CoreLectureRequirementRepository coreLectureRequirementRepository;

    public GraduationServiceImpl(GraduationRequirementRepository gradReqRepository, UserInfoRepository userInfoRepository, ScoreStatRepository scoreStatRepository, EnglishLectureRepository englishLectureRepository, InfoLectureRepository infoLectureRepository, UserSelectListRepository userSelectListRepository, CoreLectureRequirementRepository coreLectureRequirementRepository) {
        this.gradReqRepository = gradReqRepository;
        this.userInfoRepository = userInfoRepository;
        this.scoreStatRepository = scoreStatRepository;
        this.englishLectureRepository = englishLectureRepository;
        this.infoLectureRepository = infoLectureRepository;
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
        Optional<GraduationRequirementCond> requirementCond = Optional.empty();

        try {
            GraduationRequirementPK reqID = GraduationRequirementPK.builder()
                    .enrollmentYear(enrollmentYear)
                    .course(course)
                    .build();

            //졸업 요건 조회
            requirementCond = Optional.ofNullable(gradReqRepository.findById(reqID).get().toDTO());

        } catch (Exception e) {
            //처리해야하는 예외 리스트
            //"특정 학번-커리큘럼" 졸업요건 null
            log.warn("GrdService - getGraduationRequirementCond error detected: " + e.getMessage());
        }
        return requirementCond;
    }

    /**
     * "특정 사용자"가 졸업 자격이 되는지 결과 반환
     * @param user_email - 유저 이메일, must not be null.
     * @return GraduationEligibilityParam - 졸업 자격 검증 결과(DTO)
     */
    @Override
    public GraduationEligibilityParam getGraduationEligibilityParam(String user_email) {
        //user 조회
        UserInfo userInfo = userInfoRepository.findByUserid(user_email).get();

        //졸업 조건 조회
        GraduationRequirementCond condition = getGraduationRequirementCond(userInfo.getStudent_number(), userInfo.getCourse()).get();

        // 위에서 얻은 user 기본 정보를 이용하여 DB를 조회하고 필요한 정보를 생성한다.
        // 성능 개선 목적으로 다음 규약에 따라 정보를 생성한다.
        // 1) 조회(filter(where), Projection(select), aggregation(group by))만을 이용하여 DB layer 에서 처리,
        // 2) 계산은 애플리케이션 layer 에서 처리

        List<ScoreStatDTO> statList = scoreStatRepository.findByMemberId(user_email);

        //총 이수 학점 확인 - total_earned_credit
        int totalEarnedCredit = statList.stream().mapToInt(ScoreStatDTO::getCredit).sum();

        //성적 평점(평균 학점) 확인 - GPA
        double gpaTotal = statList.stream()
                .mapToDouble(stat -> stat.getGrade() * stat.getCredit())
                .sum();
        float gpa = (float) (Math.round((gpaTotal / totalEarnedCredit) * 100) / 100.0);

        //수강한 영어 강의 수 확인 - english_class
        int englishClass = (int) userSelectListRepository.countEnglishClassTaken(user_email);

        //수강한 설계 강의 수 확인 - english_class
        int designClass = (int) userSelectListRepository.countDesignClassTaken(user_email);

        //수강한 과목 정보 조회
        List<InfoLectureDTO> userSelect = userSelectListRepository.getUserSelectLectureInfoList(user_email);

        //각 공통교양, 학문기초(기본소양), 전공 등의 세부 이수 학점 확인
        int commonClassCredit = userSelect.stream()
                .filter(infoLectureDTO -> Objects.equals(infoLectureDTO.getCurriculum(), "공통교양"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        int generalClassCredit = userSelect.stream()
                .filter(infoLectureDTO -> Objects.equals(infoLectureDTO.getClassArea(), "기본소양"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        int bsmCredit = userSelect.stream()
                .filter(infoLectureDTO -> infoLectureDTO.getClassArea().contains("bsm"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        int bsmMathCredit = userSelect.stream()
                .filter(infoLectureDTO -> infoLectureDTO.getClassArea().contains("bsm_수학"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        int bsmSciCredit = userSelect.stream()
                .filter(infoLectureDTO -> infoLectureDTO.getClassArea().contains("bsm_과학"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        int majorCredit = userSelect.stream()
                .filter(infoLectureDTO -> Objects.equals(infoLectureDTO.getCurriculum(), "전공"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        int specialMajorCredit = userSelect.stream()
                .filter(infoLectureDTO -> Objects.equals(infoLectureDTO.getClassArea(), "전문"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        int leadershipCredit = userSelect.stream()
                .filter(infoLectureDTO -> Objects.equals(infoLectureDTO.getClassArea(), "리더십"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        int seminarCredit = userSelect.stream()
                .filter(infoLectureDTO -> Objects.equals(infoLectureDTO.getClassArea(), "명작"))
                .mapToInt(InfoLectureDTO::getClassCredit)
                .sum();

        // 각 EligibilityResultUnit 객체를 생성하고 eligibilityResultList 추가합니다.
        Map<String, EligibilityResultUnit> eligibilityResultList = new HashMap<>();

        eligibilityResultList.put("TotalScore", new EligibilityResultUnit(condition.getGPA(), gpa));
        eligibilityResultList.put("Register",  new EligibilityResultUnit(condition.getRegistered_semesters(), userInfo.getSemester()));
        eligibilityResultList.put("TotalCredit", new EligibilityResultUnit(condition.getTotal_earned_credit(), totalEarnedCredit));
        eligibilityResultList.put("EngClassCount", new EligibilityResultUnit(condition.getEnglish_class(), englishClass));
        eligibilityResultList.put("EngScore", new EligibilityResultUnit(condition.getToeic_score(), userInfo.getToeicScore()));
        eligibilityResultList.put("CommonClassCredit", new EligibilityResultUnit(condition.getCommonClassCredit(), commonClassCredit));
        eligibilityResultList.put("GibonsoyangCredit", new EligibilityResultUnit(condition.getGibonSoyangCredit(), generalClassCredit));
        eligibilityResultList.put("BSMCredit", new EligibilityResultUnit(condition.getBSMCredit(), bsmCredit));
        eligibilityResultList.put("BSMMathCredit", new EligibilityResultUnit(condition.getBSMMathCredit(), bsmMathCredit));
        eligibilityResultList.put("BSMSciCredit", new EligibilityResultUnit(condition.getBSMSciCredit(), bsmSciCredit));
        eligibilityResultList.put("MajorCredit", new EligibilityResultUnit(condition.getMajorCredit(), majorCredit));
        eligibilityResultList.put("SpecialMajorCredit", new EligibilityResultUnit(condition.getSpecialMajorCredit(), specialMajorCredit));
        eligibilityResultList.put("leadership_credit", new EligibilityResultUnit(condition.getLeadership_credit(), leadershipCredit));
        eligibilityResultList.put("seminar_credit", new EligibilityResultUnit(condition.getSeminar_credit(), seminarCredit));

        CoreLectureParam clp = checkEssLectureCompletion(user_email);

        boolean clpExist = clp.getNotTakingBSM().size() + clp.getNotTakingMJ().size()  + clp.getNotTakingBSM().size() <= 0;

        boolean eruSatisfaction = clpExist && eligibilityResultList.values().stream().allMatch(EligibilityResultUnit::isSatisfaction);


        GraduationEligibilityParam eligibilityParam = new GraduationEligibilityParam().builder()
                .Result(eruSatisfaction)
                .StudentNumber(userInfo.getStudent_number())
                .Course(userInfo.getCourse())
                .EngLevel(userInfo.getEnglishGrade())
                .Register(userInfo.getSemester())
                .EngScore(userInfo.getToeicScore())
                .TotalScore(gpa)
                .TotalCredit(totalEarnedCredit)
                .EngClassCount(englishClass)
                .eligibilityResultList(eligibilityResultList)
                .build();

        return eligibilityParam;

    }

    /**
     * 사용자의 필수과목 이수 현황 확인
     * @param: user_email - 유저 이메일, must not be null.
     * @return:
     */
    @Override
    public CoreLectureParam checkEssLectureCompletion(String user_email) {

        List<CoreLectureRequirement> major = null;
        List<CoreLectureRequirement> common_edu = null;
        List<CoreLectureRequirement> general_edu = null;
        List<InfoLecture> user_select;

        List<String> major_result = new ArrayList<String>();
        List<String> common_edu_result = new ArrayList<String>();
        List<String> general_edu_result = new ArrayList<String>();

        try {
            //user 조회
            UserInfo userInfo = userInfoRepository.findByUserid(user_email).get();

            //필수과목 리스트 추출
            major = coreLectureRequirementRepository.getLectureList(userInfo.getStudent_number(), userInfo.getCourse(), "전공필수");
            common_edu = coreLectureRequirementRepository.getLectureList(userInfo.getStudent_number(), userInfo.getCourse(), "공통교양");
            general_edu = coreLectureRequirementRepository.getLectureList(userInfo.getStudent_number(), userInfo.getCourse(), "기본소양");

            //수강한 과목 리스트 추출
            user_select = userSelectListRepository.getUserSelectLectureNicknameList(user_email);

            Map<String, Integer> select_subject = new HashMap<String, Integer>();
            //전공 과목 체크
            check_subject_category(major, user_select, select_subject);

            //공통 교양 체크
            check_subject_category(common_edu, user_select, select_subject);

            //기본 소양 체크
            check_subject_category(general_edu, user_select, select_subject);


            for(int i=0; i<major.size(); i++) {
                major_result.add(major.get(i).getId().getLectureName());
            }

            for(int i=0; i<common_edu.size(); i++) {
                common_edu_result.add(common_edu.get(i).getId().getLectureName());
            }

            for(int i=0; i<general_edu.size(); i++) {
                general_edu_result.add(general_edu.get(i).getId().getLectureName());
            }



        } catch (Exception e) {
            log.error("GrdService - checkEssLectureCompletion error detected: " + e.getMessage());
        }
        CoreLectureParam coreLectureParam = new CoreLectureParam().builder()
                .notTakingNC(common_edu_result)
                .notTakingBSM(general_edu_result)
                .notTakingMJ(major_result)
                .build();
        return coreLectureParam;


    }

    private void check_subject_category(List<CoreLectureRequirement> general_edu, List<InfoLecture> user_select, Map<String, Integer> select_subject) {
        for(int i =0; i < general_edu.size(); i++){

            for(int j = 0; j < user_select.size(); j++){
                if(general_edu.get(i).getCardinality().equals("수")) {
                    if(user_select.get(j).getClassNumber().equals(general_edu.get(j).getId().getLecture_number()))
                        general_edu.remove(i);
                } else {
                    if(user_select.get(j).getClassNumber().equals(general_edu.get(j).getId().getLecture_number())){
                        String category =  general_edu.get(j).getCategory();
                        Integer max = general_edu.get(j).getMax_num();
                        if(select_subject.containsKey(category)) {
                            if(select_subject.get(category).equals(0)){
                                general_edu.remove(i);
                            } else {
                                select_subject.put(category, select_subject.get(category) - 1);
                            }
                        } else {
                            select_subject.put(category, max);
                        }
                    }
                }
            }
        }
    }
}


