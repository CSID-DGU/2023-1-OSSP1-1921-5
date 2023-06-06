package graduationProject.graduation_judge.DTO.Member;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoDTO {
    private String userid;
    private String pincode;
    private int semester;
    private int student_number;
    private Major_curriculum course;
    private int toeicScore;
    private English_level englishGrade;

    public UserInfo toEntity(){
        return UserInfo.builder()
                .userid(userid)
                .pincode(pincode)
                .semester(semester)
                .student_number(student_number)
                .course(course)
                .toeicScore(toeicScore)
                .englishGrade(englishGrade)
                .build();
    }
}
