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
@Table(name = "ScoreStat")
public class ScoreStat {

    @Id
    @Column(name = "UID", length = 200, nullable = false)
    private String UID;

    @Id
    @Column(name = "Semester", nullable = false)
    private int semester;

    @Id
    @Column(name = "TypeID", length = 200, nullable = false)
    private String typeId;

    @Column(name = "Grade", length = 200, nullable = false)
    private String grade;

    @Column(name = "Credit", length = 200, nullable = false)
    private String credit;

}
