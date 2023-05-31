package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.EnglishLecturePK;
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
@IdClass(EnglishLecturePK.class)
@Table(name = "english_lecture")
public class EnglishLecture {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "english_term_number", length = 200, nullable = false)
    private String termNum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "english_class_number", length = 200, nullable = false)
    private String classNum;

    //1:1 EntireLecture
    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "english_term_number"),
        @JoinColumn(name = "english_class_number")
    })
    private EntireLecture eng_entireLecture;
}
