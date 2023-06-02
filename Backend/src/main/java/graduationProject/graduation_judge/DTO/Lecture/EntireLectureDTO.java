package graduationProject.graduation_judge.DTO.Lecture;

import graduationProject.graduation_judge.DAO.EnglishLecture;
import graduationProject.graduation_judge.DAO.EntireLecture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntireLectureDTO {
    String termNum;
    String classNum;
    String professorName;

    public EntireLecture toEntity() {
        return EntireLecture.builder()
                .term_number(termNum)
                .class_number(classNum)
                .professorName(professorName)
                .build();
    }
}
