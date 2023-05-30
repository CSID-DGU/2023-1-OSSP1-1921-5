package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.domain.Stats.ScoreStatId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ScoreStatId.class)
@Table(name = "score_stat")
public class ScoreStat {

    @Id
    @Column(name = "user_id", length = 200, nullable = false)
    private String memberId;

    @Id
    @Column(name = "semester", nullable = false)
    private int semester;

    @Id
    @Column(name = "type_id", length = 200, nullable = false)
    private String typeId;

    @Column(name = "grade", length = 200, nullable = false)
    private String grade;

    @Column(name = "credit", length = 200, nullable = false)
    private String credit;

}
