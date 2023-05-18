package graduationProject.graduation_judge.domain.Grade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userselectlist")
public final class Grade {

    @Id
    @Column(name = "UserID", nullable = false)
    private String memberId;

    @Id
    @Column(name = "TNumber", nullable = false)
    private String termNum;

    @Id
    @Column(name = "CNumber", nullable = false)
    private String classNum;

    @Column(name = "ClassScore", nullable = false)
    private String score;
}
