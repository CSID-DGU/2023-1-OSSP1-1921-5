package graduationProject.graduation_judge.DAO;

<<<<<<< HEAD
import graduationProject.graduation_judge.domain.Stats.ScoreStatId;
=======
import graduationProject.graduation_judge.DAO.identifier.ScoreStatPK;
>>>>>>> 13acf0a (feat: DAO 연관관계 매핑 추가)
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
=======
import java.io.Serializable;
import java.util.List;

>>>>>>> 13acf0a (feat: DAO 연관관계 매핑 추가)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
<<<<<<< HEAD
@IdClass(ScoreStatId.class)
=======
@IdClass(ScoreStatPK.class)
>>>>>>> 13acf0a (feat: DAO 연관관계 매핑 추가)
@Table(name = "score_stat")
public class ScoreStat {

    @Id
<<<<<<< HEAD
    @Column(name = "user_id", length = 200, nullable = false)
    private String memberId;
=======
    @Column(name = "user_id", length = 200, nullable = false, insertable=false, updatable=false)
    private String UID;
>>>>>>> 13acf0a (feat: DAO 연관관계 매핑 추가)

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
