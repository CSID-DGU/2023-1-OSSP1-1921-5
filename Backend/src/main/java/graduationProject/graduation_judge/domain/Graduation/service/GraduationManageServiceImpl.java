package graduationProject.graduation_judge.domain.Graduation.service;

import graduationProject.graduation_judge.DAO.CoreLectureRequirement;
import graduationProject.graduation_judge.DAO.GraduationRequirement;
import graduationProject.graduation_judge.DAO.identifier.CoreLectureRequirementPK;
import graduationProject.graduation_judge.DAO.identifier.GraduationRequirementPK;
import graduationProject.graduation_judge.DTO.Graduation.GraduationReqInput;
import graduationProject.graduation_judge.DTO.Graduation.GraduationReqModiInput;
import graduationProject.graduation_judge.DTO.Graduation.GraduationReqNewInput;
import graduationProject.graduation_judge.domain.Graduation.repository.CoreLectureRequirementRepository;
import graduationProject.graduation_judge.domain.Graduation.repository.GraduationRequirementRepository;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
public class GraduationManageServiceImpl implements GraduationManageService {

    private final GraduationRequirementRepository gradRequirementRepository;
    private final CoreLectureRequirementRepository coreLectureRequirementRepository;

    public GraduationManageServiceImpl(GraduationRequirementRepository gradRequirementRepository, CoreLectureRequirementRepository coreLectureRequirementRepository) {
        this.gradRequirementRepository = gradRequirementRepository;
        this.coreLectureRequirementRepository = coreLectureRequirementRepository;
    }
    @Override
    public void addGraduationRequirement(Map<String, GraduationReqInput> reqInput, int enrollment, Major_curriculum curriculum) {
        for (Map.Entry<String, GraduationReqInput> entry : reqInput.entrySet()) {
            String key = entry.getKey();
            GraduationReqInput value = entry.getValue();
            GraduationRequirementPK requirementPK = new GraduationRequirementPK(enrollment, curriculum);

            
            int english_score = value.getEnglishExamScore();
            int total_credit = value.getTotalCredit();
            float gpa = value.getGpa();
            int englishClass = value.getEnglishLecture();
            int bsm_credit = value.getBsmCredit();
            int common_credit = value.getCommonCredit();
            int general_credit = value.getGeneralCredit();
            int major_credit = value.getMajorCredit();

            System.out.println("enrollment" + enrollment);
            System.out.println("curriculum" +curriculum);
            // 데이터 처리 로직을 수행

            if(key == "0") {
                boolean isExist = gradRequirementRepository.existsById(requirementPK);
                System.out.println("isExist?" + isExist);
                // 존재하는 입학년도-커리큘럼의 경우
                if (isExist) {
                    gradRequirementRepository.updateRequirement(
                            english_score,
                            total_credit,
                            gpa,
                            englishClass,
                            bsm_credit,
                            common_credit,
                            general_credit,
                            major_credit,
                            requirementPK
                    );

                } else {
                    // 존재하지 않는 입학년도-커리큘럼의 경우
                    GraduationRequirement gr = new GraduationRequirement().builder()
                            .id(requirementPK)
                            .majorCredit(major_credit)
                            .bsmCredit(bsm_credit)
                            .registeredSemesters(8)
                            .englishClass(englishClass)
                            .englishScore(english_score)
                            .totalEarnedCredit(total_credit)
                            .gpa(gpa)
                            .commonEducationCredit(common_credit)
                            .generalEducationCredit(general_credit)
                            .majorCredit(major_credit)
                            .build();

                    gradRequirementRepository.save(gr);

                }
            }

            CoreLectureRequirement cr = new CoreLectureRequirement();
            CoreLectureRequirementPK crpk = new CoreLectureRequirementPK();
            crpk.setEnrollmentYear(enrollment);
            crpk.setCourse(String.valueOf(curriculum));
            crpk.setLectureName(value.getLectureName());
            crpk.setLecture_number(value.getLectureNumber());

            if(value.getComment().equals("필수")) {
               cr.setCardinality("필수");
            } else {
                String text =  value.getComment();
                String extractedNumber = text.substring(text.length() - 1); // 마지막 문자 추출
                int number = Integer.parseInt(extractedNumber); // 문자열을 정수로 변환
                cr.setMax_num(number);
                cr.setCardinality("선택");
            }

            cr.setId(crpk);
            cr.setCategory(value.getCategory());

            //필수 과목 저장
            coreLectureRequirementRepository.save(cr);
        }
    }

    @Override
    public void addNewGraduationRequirement(Map<String, GraduationReqNewInput> reqInput, int enrollment, Major_curriculum curriculum) {
        for (Map.Entry<String, GraduationReqNewInput> entry : reqInput.entrySet()) {
            String key = entry.getKey();
            GraduationReqNewInput value = entry.getValue();
            String category = value.getCategory();
            String lectureName =  value.getLectureName();
            System.out.println("lectureName" + lectureName);

            GraduationRequirementPK requirementPK = new GraduationRequirementPK(enrollment, curriculum);
            // 데이터 처리 로직을 수행
            CoreLectureRequirement ncr = new CoreLectureRequirement();
            CoreLectureRequirementPK ncrpk = new CoreLectureRequirementPK();
            ncrpk.setEnrollmentYear(enrollment);
            ncrpk.setCourse(String.valueOf(curriculum));
            ncrpk.setLectureName(lectureName);
            ncrpk.setLecture_number(value.getLectureNumber());


            if(value.getComment().equals("필수")) {
                ncr.setCardinality("필수");
            } else {
                String text =  value.getComment();
                String extractedNumber = text.substring(text.length() - 1); // 마지막 문자 추출
                int number = Integer.parseInt(extractedNumber); // 문자열을 정수로 변환
                ncr.setMax_num(number);
                ncr.setCardinality("선택");
            }


            ncr.setId(ncrpk);
            ncr.setCategory(category);

            //필수 과목 저장
            coreLectureRequirementRepository.save(ncr);

        }
    }

    @Override
    public void ModiGraduationRequirement(Map<String, GraduationReqModiInput> reqInput, int enrollment, Major_curriculum curriculum) {
        for (Map.Entry<String, GraduationReqModiInput> entry : reqInput.entrySet()) {
            String key = entry.getKey();
            GraduationReqModiInput value = entry.getValue();
            String prevlectureNumber = value.getPrevLectureNumber();

            String lectureName =  value.getLectureName();
            System.out.println("lectureName" + lectureName);

            CoreLectureRequirementPK prePK = new CoreLectureRequirementPK(prevlectureNumber, value.getLectureName(), curriculum, enrollment);

            CoreLectureRequirement prevreq = coreLectureRequirementRepository.findById(prePK).get();
            CoreLectureRequirementPK prevreqPK = prevreq.getId();

            coreLectureRequirementRepository.updateCardinalityAndMax_numById("선택", prevreq.getMax_num()+1, prevreqPK);
            prevreqPK.setLecture_number(value.getNewLectureNumber());
            prevreq.setId(prevreqPK);

            coreLectureRequirementRepository.save(prevreq);

        }
    }


}


