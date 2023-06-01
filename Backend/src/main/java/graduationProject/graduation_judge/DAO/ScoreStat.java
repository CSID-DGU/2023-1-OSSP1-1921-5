package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.ScoreStatPK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(ScoreStatPK.class)
@Table(name = "score_stat")
public class ScoreStat {

    @Id
    @Column(name = "user_id", length = 200, nullable = false, insertable=false, updatable=false)
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

    //M:1 UserInfo
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @Builder
    public ScoreStat(String memberId, int semester, String typeId, String grade, String credit){
        this.memberId = memberId;
        this.semester = semester;
        this.typeId = typeId;
        this.grade = grade;
    }

}
