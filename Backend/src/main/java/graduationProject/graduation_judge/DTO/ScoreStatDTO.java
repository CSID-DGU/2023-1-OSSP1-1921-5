package graduationProject.graduation_judge.DTO;

import graduationProject.graduation_judge.DAO.ScoreStat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreStatDTO {
    private String memberId;
    private int semester;
    private String typeId;
    private float grade;
    private int credit;

    public ScoreStat toEntity(){
        return ScoreStat.builder()
                .memberId(memberId)
                .semester(semester)
                .typeId(typeId)
                .grade(grade)
                .credit(credit)
                .build();
    }
}
