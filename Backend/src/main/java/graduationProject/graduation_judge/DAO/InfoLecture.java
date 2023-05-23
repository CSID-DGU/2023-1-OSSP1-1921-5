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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "info_lecture")
public class InfoLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Info_ClassNumber", length = 200, nullable = false)
    private String classNum;

    @Column(name = "LectureNick", length = 200, nullable = false)
    private String lectureNick;

    @Column(name = "Curriculum", length = 200, nullable = false)
    private String curriculum;

    @Column(name = "ClassArea", length = 200)
    private String classArea;

    @Column(name = "ClassCredit", nullable = false)
    private int classCredit;


}
