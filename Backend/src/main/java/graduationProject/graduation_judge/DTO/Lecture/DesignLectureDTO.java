package graduationProject.graduation_judge.DTO.Lecture;

import graduationProject.graduation_judge.DAO.DesignLecture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DesignLectureDTO {
    private String termNum;
    private String classNum;
    private Float designCredit;

    public DesignLecture toEntity() {
        return DesignLecture.builder()
                .termNum(termNum)
                .classNum(classNum)
                .designCredit(designCredit)
                .build();
    }
}
