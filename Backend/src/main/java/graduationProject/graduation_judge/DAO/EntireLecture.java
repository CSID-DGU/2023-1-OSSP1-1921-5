package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.EntireLecturePK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.mapping.ToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EntireLecturePK.class)
@Table(name = "entire_lecture")
public class EntireLecture {
    @Id
    @Column(name = "term_number", length = 200, nullable = false, insertable=false, updatable=false)
    private String term_number;

    @Id
    @Column(name = "class_number", length = 200, nullable = false, insertable=false, updatable=false)
    private String class_number;

    @Column(name = "professor_name")
    private String professorName;

//    //1:1 InfoLecture
//    @OneToOne
//    @JoinColumn(name = "class_number", referencedColumnName = "info_class_number" ,nullable = false)
//    private InfoLecture infoLectures;

    @OneToOne(mappedBy = "entireLecture")
    private InfoLecture infoLecture;

    //1:1 EnglishLecture
    @OneToOne(mappedBy = "eng_entireLecture")
    private EnglishLecture englishLecture;

    //1:1 DesignLecture
    @OneToOne(mappedBy = "des_entireLecture")
    private DesignLecture designLecture;

    //1:M UserSelectList
    @OneToMany(mappedBy = "us_entireLecture")
    private List<UserSelectList> userSelectList;

}
