package graduationProject.graduation_judge.DTO.Lecture;

import graduationProject.graduation_judge.DAO.InfoLecture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoLectureDTO {
    private String lectureNick;
    private String curriculum;
    private String classArea;
    private  int classCredit;
    private String classNumber;

    public InfoLecture toEntity() {
        return InfoLecture.builder()
                .lectureNick(lectureNick)
                .curriculum(curriculum)
                .classArea(classArea)
                .classCredit(classCredit)
                .classNumber(classNumber)
                .build();
    }
}
