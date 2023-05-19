package graduationProject.graduation_judge.domain.Stats;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ScoreStat")
public final class Stats {
    @Id
    @Column(name = "UID", nullable = false)
    private String memberId;

    @Id
    @Column(name = "Semester", nullable = false)
    private Integer semester;

    @Id
    @Column(name = "TypeID", nullable = false)
    private String typeId;

    @Column(name = "Grade")
    private String grade;

    @Column(name = "Credit")
    private String credit;
}
