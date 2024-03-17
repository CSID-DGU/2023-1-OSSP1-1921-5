package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
//@Table(name = "user_info", indexes = @Index(name = "idx_user_id", columnList = "user_id", unique = true)) //테이블, 인덱스 테이블 지정
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "user_id", length = 200, nullable = false)
    private String userid;

    @Column(name = "pincode", length = 200, nullable = false)
    private String pincode;

    @Column(name = "semester", nullable = false)
    private int semester;

    @Column(name = "student_number", nullable = false)
    private int student_number;

    @Column(name = "course", length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private Major_curriculum course;

    @Column(name = "toeic_score", nullable = false)
    private int toeicScore;

    @Column(name = "english_grade", length = 200, nullable = false) //columnDefinition = "tinyint"
    @Enumerated(EnumType.STRING)
    private English_level englishGrade;

    //1:M UserSelectList
    @OneToMany(mappedBy = "userid", fetch = FetchType.LAZY)
    private List<UserSelectList> userSelectList;

    //1:M ScoreStat
    @OneToMany(mappedBy = "userInfo")
    private List<ScoreStat> scoreStats;

    @Builder
    public UserInfo(String userid, String pincode,
                    int semester, int student_number,
                    Major_curriculum course,
                    int toeicScore, English_level englishGrade){
        this.userid = userid;
        this.pincode = pincode;
        this.semester = semester;
        this.student_number = student_number;
        this.course = course;
        this.toeicScore = toeicScore;
        this.englishGrade = englishGrade;
    }
}
