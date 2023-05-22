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
@Table(name = "english_lecture")
public class EnglishLecture {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "English_TermNumber", length = 200, nullable = false)
    private String termNum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "English_ClassNumber", length = 200, nullable = false)
    private String classNum;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "termNum", referencedColumnName = "TermNumber"),
        @JoinColumn(name = "classNum", referencedColumnName = "ClassNumber")
    })
    private EntireLecture entireLecture;
}
