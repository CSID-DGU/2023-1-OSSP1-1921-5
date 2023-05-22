package graduationProject.graduation_judge.DAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import graduationProject.graduation_judge.domain.Grade.GradeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@IdClass(GradeId.class)
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
