package graduationProject.graduation_judge.DAO;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
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

    @Builder
    public InfoLecture(String lectureNick, String curriculum, String classArea, int classCredit, String classNumber){
        this.lectureNick = lectureNick;
        this.curriculum = curriculum;
        this.classArea = classArea;
        this.classCredit = classCredit;
        this.classNumber = classNumber;
    }

//    @OneToOne
//    @JoinColumn(name = "class_number")
//    private EntireLecture entireLecture;

//    //1:1 EntireLecture
//    @OneToOne(mappedBy = "infoLectures")
//    private EntireLecture entireLecture;

}
