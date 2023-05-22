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
@Table(name = "entire_lecture")
public class EntireLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TermNumber ", nullable = false)
    private String termNum;

    @Id
    @Column(name = "ClassNumber", nullable = false)
    private String classNum;

    @Column(name = "ClassNumber")
    private String professorName;
}
