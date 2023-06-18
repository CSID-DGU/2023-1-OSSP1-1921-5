package graduationProject.graduation_judge.domain.Graduation.service;

import graduationProject.graduation_judge.DTO.Graduation.CoreLectureParam;
import graduationProject.graduation_judge.DTO.Graduation.GraduationReqInput;
import graduationProject.graduation_judge.DTO.Graduation.GraduationReqNewInput;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GraduationManageService {

    public void addGraduationRequirement(Map<String, GraduationReqInput> reqInput, int enrollment, Major_curriculum curriculum);

    public void addNewGraduationRequirement(Map<String, GraduationReqNewInput> reqInput, int enrollment, Major_curriculum curriculum);

}
