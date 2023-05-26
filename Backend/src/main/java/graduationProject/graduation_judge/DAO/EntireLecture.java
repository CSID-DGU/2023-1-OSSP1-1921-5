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
    @Column(name = "term_number", length = 200, nullable = false)
    private String termNum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_number", length = 200, nullable = false)
    private String classNum;

    @Column(name = "professor_name")
    private String professorName;

    @ManyToOne
    @JoinColumn(name = "class_number", referencedColumnName = "info_class_number")
    private InfoLecture infoLecture;
}
