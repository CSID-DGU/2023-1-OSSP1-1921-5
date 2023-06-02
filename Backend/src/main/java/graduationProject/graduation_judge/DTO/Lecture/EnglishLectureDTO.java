package graduationProject.graduation_judge.DTO.Lecture;

import graduationProject.graduation_judge.DAO.EnglishLecture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnglishLectureDTO {
    private String termNum;
    private String classNum;

    public EnglishLecture toEntity(){
        return EnglishLecture.builder()
                .termNum(termNum)
                .classNum(classNum)
                .build();
    }
}
