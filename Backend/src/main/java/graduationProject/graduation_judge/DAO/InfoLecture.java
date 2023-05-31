package graduationProject.graduation_judge.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 13acf0a (feat: DAO 연관관계 매핑 추가)
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "info_lecture")
public class InfoLecture {

    @Column(name = "lecture_nick", length = 200, nullable = false)
    private String lectureNick;

    @Column(name = "curriculum", length = 200, nullable = false)
    private String curriculum;

    @Column(name = "class_area", length = 200)
    private String classArea;

    @Column(name = "class_credit", nullable = false)
    private int classCredit;

    @Id
    @Column(name = "info_class_number", length = 200, nullable = false)
    private String classNumber;


    @OneToOne
    @JoinColumn(name = "class_number")
    private EntireLecture entireLecture;
//    //1:1 EntireLecture
//    @OneToOne(mappedBy = "infoLectures")
//    private EntireLecture entireLecture;

}
