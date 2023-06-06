package graduationProject.graduation_judge.DTO.Member.MyPage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMyPageInfoDTO {
    private String studentNumber;
    private String semester;
    private String course;
    private String englishGrade;
    private String score;
}
