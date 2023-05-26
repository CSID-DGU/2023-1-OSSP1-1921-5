package graduationProject.graduation_judge.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "score_stat")
public class ScoreStat {

    @Id
    @Column(name = "user_id", length = 200, nullable = false)
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

}
