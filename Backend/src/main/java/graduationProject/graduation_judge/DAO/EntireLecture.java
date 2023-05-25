package graduationProject.graduation_judge.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entire_lecture")
public class EntireLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TermNumber", length = 200, nullable = false)
    private String termNum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassNumber", length = 200, nullable = false)
    private String classNum;

    @Column(name = "ProfessorName")
    private String professorName;

    @ManyToOne
    @JoinColumn(name = "classNumber", referencedColumnName = "Info_ClassNumber")
    private InfoLecture infoLecture;
}
