package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.domain.Stats.ScoreStatId;
import graduationProject.graduation_judge.DAO.identifier.ScoreStatPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ScoreStatPK.class)
@Table(name = "score_stat")
public class ScoreStat {

    @Id
    @Column(name = "user_id", length = 200, nullable = false)
    private String memberId;

    @Column(name = "user_id", length = 200, nullable = false, insertable=false, updatable=false)
    private String UID;

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

    //M:1 UserInfo
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

}
