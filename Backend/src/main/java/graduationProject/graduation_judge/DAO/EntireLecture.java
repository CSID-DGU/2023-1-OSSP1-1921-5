package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.EntireLecturePK;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.mapping.ToOne;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(EntireLecturePK.class)
@Table(name = "entire_lecture")
public class EntireLecture {
    @Id
    @Column(name = "term_number", length = 200, nullable = false)
    private String termNumber;

    @Id
    @Column(name = "class_number", length = 200, nullable = false)
    private String classNumber;

    @Column(name = "professor_name")
    private String professorName;

    //1:1 InfoLecture
    @OneToOne
    @JoinColumn(name = "class_number", referencedColumnName = "info_class_number" ,nullable = false, insertable=false, updatable=false)
    private InfoLecture infoLecture;

//    @OneToOne(mappedBy = "entireLecture")
//    private InfoLecture infoLecture;

    //1:1 EnglishLecture
    @OneToOne(mappedBy = "eng_entireLecture")
    private EnglishLecture englishLecture;

    //1:1 DesignLecture
    @OneToOne(mappedBy = "des_entireLecture")
    private DesignLecture designLecture;

    //1:M UserSelectList
    @OneToMany(mappedBy = "entireLecture")
    private List<UserSelectList> userSelectList;

    @Builder
    public EntireLecture(String termNumber, String classNumber, String professorName){
        this.termNumber =termNumber;
        this.classNumber = classNumber;
        this.professorName = professorName;
    }
}
