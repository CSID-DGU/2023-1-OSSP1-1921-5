package graduationProject.graduation_judge.domain.Graduation.service;

import graduationProject.graduation_judge.DAO.CoreLectureRequirement;
import graduationProject.graduation_judge.DAO.InfoLecture;
import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import graduationProject.graduation_judge.DTO.Graduation.CoreLectureParam;
import graduationProject.graduation_judge.DTO.Graduation.Eligibility_Result_Unit;
import graduationProject.graduation_judge.DTO.Graduation.GraduationEligibilityParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationRequirementCond;
import graduationProject.graduation_judge.DTO.Lecture.InfoLectureDTO;
import graduationProject.graduation_judge.DTO.Stats.ScoreStatDTO;
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

import java.util.*;
import java.util.stream.Stream;

@Slf4j
@Service
public class GraduationServiceImpl implements GraduationService{
    private final UserInfoRepository userInfoRepository;
    private final GraduationRequirementRepository gradReqRepository;
    private final ScoreStatRepository scoreStatRepository;
    private final EnglishLectureRepository englishLectureRepository;

    private final UserSelectListRepository userSelectListRepository;

    private  final CoreLectureRequirementRepository coreLectureRequirementRepository;

    public GraduationServiceImpl(GraduationRequirementRepository gradReqRepository, UserInfoRepository userInfoRepository, ScoreStatRepository scoreStatRepository, EnglishLectureRepository englishLectureRepository, UserSelectListRepository userSelectListRepository, CoreLectureRequirementRepository coreLectureRequirementRepository) {
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
        Major_curriculum course = null;

        int enrollmentYear = -1;
        English_level engLevel = null;

        int total_taken_subject = 0;

        int semester = 0;

        int toeic_score = 0;

        int total_earned_credit = 0;

        float GPA = 0;

        int english_class = 0;

        int design_class = 0;

        int common_class_credit = 0;

        int general_class_credit = 0;

        int bsm_credit = 0;

        int bsm_math_credit = 0;

        int bsm_sci_credit = 0;

        int major_credit = 0;

        int special_major_credit = 0;

        int leadership_credit = 0;

        int seminar_credit = 0;



        List<InfoLectureDTO> user_select = new ArrayList<>();

        //user 조회
        UserInfo userInfo = userInfoRepository.findByUserid(user_email).get();

        //학번과 커리큘럼 추출
        enrollmentYear = userInfo.getStudent_number();
        course = userInfo.getCourse();

        //영어레벨 추출
        engLevel = userInfo.getEnglishGrade();

        //user 등록 학기 추출
        semester = userInfo.getSemester();

        //user 토익 점수 추출
        toeic_score = userInfo.getToeicScore();

        //졸업 조건 조회
        GraduationRequirementCond condition = getGraduationRequirementCond(enrollmentYear, course).get();


        HashMap<String, Eligibility_Result_Unit> Eligibility_Result_List = new HashMap<>();






        try {

            // 위에서 얻은 user 기본 정보를 이용하여 DB를 조회하고 필요한 정보를 생성한다.
            // 성능 개선 목적으로 다음 규약에 따라 정보를 생성한다.
            // 1) 조회(filter(where), Projection(select), aggregation(group by))만을 이용하여 DB layer 에서 처리,
            // 2) 계산은 애플리케이션 layer 에서 처리

            List<ScoreStatDTO> stat_list = scoreStatRepository.findByMemberId(user_email);

            //총 이수 학점 확인 - total_earned_credit
            Iterator<ScoreStatDTO> stat_iterator = stat_list.iterator();
            while (stat_iterator.hasNext()) {
                ScoreStatDTO stat = stat_iterator.next();
                total_earned_credit += stat.getCredit();
            }
            log.info("total_earned_credit: " + total_earned_credit);

            //성적 평점(평균 학점) 확인 - GPA
            stat_iterator = stat_list.iterator();
            while (stat_iterator.hasNext()) {
                ScoreStatDTO stat = stat_iterator.next();
                float semester_grade = stat.getGrade();
                int semester_credit = stat.getCredit();
                String stat_type = stat.getTypeId();
                GPA += semester_grade * semester_credit;
            }
            GPA = (float) (Math.round((GPA / total_earned_credit) * 100) / 100.0);
//            log.info("GPA: " + GPA);

            //수강한 영어 강의 수 확인 - english_class
            english_class = (int) userSelectListRepository.countEnglishClassTaken(user_email);
//            log.info("english_class: " + english_class);

            //수강한 설계 강의 수 확인 - english_class
            design_class = (int) userSelectListRepository.countDesignClassTaken(user_email);
//            log.info("design_class: " + design_class);

            //수강한 과목 정보 조회
            user_select = userSelectListRepository.getUserSelectLectureInfoList(user_email);

            //각 공통교양, 학문기초(기본소양), 전공 등의 세부 이수 학점 확인
            Iterator<InfoLectureDTO> info_lecture_iterator = user_select.iterator();
            while (info_lecture_iterator.hasNext()) {
                InfoLectureDTO infoLectureDTO = info_lecture_iterator.next();
                int credit = infoLectureDTO.getClassCredit();
                String Curriculum = infoLectureDTO.getCurriculum();
                String ClassArea = infoLectureDTO.getClassArea();

                if(Objects.equals(Curriculum, "공통교양")){
                    common_class_credit += credit;
                }

                if(Objects.equals(ClassArea, "기본소양")) {
                    general_class_credit += credit;
                }

                if(ClassArea.contains("bsm")) {
                    bsm_credit += credit;
                    if(ClassArea.contains("bsm_수학"))
                        bsm_math_credit += credit;
                    if(ClassArea.contains("bsm_과학"))
                        bsm_sci_credit += credit;
                }

                if(Objects.equals(Curriculum, "전공")) {
                    major_credit += credit;
                    if(ClassArea.equals("전문")) { //개별연구에 해당함
                        special_major_credit += credit;
                    }
                }

                //리더십 과목
                if(ClassArea.equals("리더십")) {
                    leadership_credit += credit;
                }

                //세미나 과목
                if(ClassArea.equals("명작")) {
                    seminar_credit += credit;
                }
            }



        } catch (Exception e) {
            log.error("GrdService - getGraduationEligibilityParam error detected: " + e.getMessage());
        } finally {

            Eligibility_Result_Unit eru_TotalScore = new Eligibility_Result_Unit(condition.getGPA(), GPA);
            Eligibility_Result_Unit eru_Register = new Eligibility_Result_Unit(condition.getRegistered_semesters(), semester);
            Eligibility_Result_Unit eru_TotalCredit = new Eligibility_Result_Unit(condition.getTotal_earned_credit(), total_earned_credit);
            Eligibility_Result_Unit eru_EngClassCount = new Eligibility_Result_Unit(condition.getEnglish_class(), english_class);
            Eligibility_Result_Unit eru_EngScore = new Eligibility_Result_Unit(condition.getToeic_score(), toeic_score);
            Eligibility_Result_Unit eru_CommonClassCredit = new Eligibility_Result_Unit(condition.getCommonClassCredit(), common_class_credit);
            Eligibility_Result_Unit eru_GibonsoyangCredit = new Eligibility_Result_Unit(condition.getGibonSoyangCredit(), general_class_credit);
            Eligibility_Result_Unit eru_BSMCredit = new Eligibility_Result_Unit(condition.getBSMCredit(), bsm_credit);
            Eligibility_Result_Unit eru_BSMMathCredit = new Eligibility_Result_Unit(condition.getBSMMathCredit(), bsm_math_credit);
            Eligibility_Result_Unit eru_BSMSciCredit = new Eligibility_Result_Unit(condition.getBSMSciCredit(), bsm_sci_credit);
            Eligibility_Result_Unit eru_MajorCredit = new Eligibility_Result_Unit(condition.getMajorCredit(), major_credit);
            Eligibility_Result_Unit eru_SpecialMajorCredit = new Eligibility_Result_Unit(condition.getSpecialMajorCredit(), special_major_credit);
            Eligibility_Result_Unit eru_leadership_credit = new Eligibility_Result_Unit(condition.getLeadership_credit(), leadership_credit);
            Eligibility_Result_Unit eru_seminar_credit = new Eligibility_Result_Unit(condition.getSeminar_credit(), seminar_credit);

            Eligibility_Result_List.put("TotalScore", eru_TotalScore);
            Eligibility_Result_List.put("Register", eru_Register);
            Eligibility_Result_List.put("TotalCredit", eru_TotalCredit);
            Eligibility_Result_List.put("EngClassCount", eru_EngClassCount);
            Eligibility_Result_List.put("EngScore", eru_EngScore);
            Eligibility_Result_List.put("CommonClassCredit", eru_CommonClassCredit);
            Eligibility_Result_List.put("GibonsoyangCredit", eru_GibonsoyangCredit);
            Eligibility_Result_List.put("BSMCredit", eru_BSMCredit);
            Eligibility_Result_List.put("BSMMathCredit", eru_BSMMathCredit);
            Eligibility_Result_List.put("BSMSciCredit", eru_BSMSciCredit);
            Eligibility_Result_List.put("MajorCredit", eru_MajorCredit);
            Eligibility_Result_List.put("SpecialMajorCredit", eru_SpecialMajorCredit);
            Eligibility_Result_List.put("leadership_credit", eru_leadership_credit);
            Eligibility_Result_List.put("seminar_credit", eru_seminar_credit);

            CoreLectureParam clp = checkEssLectureCompletion(user_email);

            boolean clp_isExist = true;
            if (clp.getNotTakingBSM().size()
                    + clp.getNotTakingMJ().size()
                    + clp.getNotTakingBSM().size() > 0) {
                clp_isExist = false;
            }

            boolean eru_satisfaction =
                    eru_TotalScore.isSatisfaction() && eru_Register.isSatisfaction() && eru_TotalCredit.isSatisfaction()
                            && eru_EngClassCount.isSatisfaction() && eru_EngScore.isSatisfaction() && eru_CommonClassCredit.isSatisfaction()
                            && eru_GibonsoyangCredit.isSatisfaction() && eru_BSMCredit.isSatisfaction() && eru_BSMMathCredit.isSatisfaction()
                            && eru_BSMSciCredit.isSatisfaction() && eru_MajorCredit.isSatisfaction() && eru_SpecialMajorCredit.isSatisfaction()
                            && eru_leadership_credit.isSatisfaction() && eru_seminar_credit.isSatisfaction() && clp_isExist;


            GraduationEligibilityParam eligibilityParam = new GraduationEligibilityParam().builder()
                    .Result(eru_satisfaction)
                    .StudentNumber(enrollmentYear)
                    .Course(course)
                    .EngLevel(engLevel)
                    .Register(semester)
                    .EngScore(toeic_score)
                    .TotalScore(GPA)
                    .TotalCredit(total_earned_credit)
                    .EngClassCount(english_class)
                    .Eligibility_Result_List(Eligibility_Result_List)
                    .build();

//            log.info("testing Result: " + eligibilityParam.getTotalScore());
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

            //학번과 커리큘럼 추출
            int enrollmentYear = userInfo.getStudent_number();
            Major_curriculum course = userInfo.getCourse();

            //필수과목 리스트 추출
            major = coreLectureRequirementRepository.getLectureList(enrollmentYear, course, "전공필수");
            common_edu = coreLectureRequirementRepository.getLectureList(enrollmentYear, course, "공통교양");
            general_edu = coreLectureRequirementRepository.getLectureList(enrollmentYear, course, "기본소양");

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


