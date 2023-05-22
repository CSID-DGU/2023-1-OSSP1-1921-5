package graduationProject.graduation_judge.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserInfo")
public class UserInfo {

    @Id
    @Column(name = "ID", length = 200, nullable = false)
    private String id;

    @Column(name = "Pincode", length = 200, nullable = false)
    private String pincode;

    @Column(name = "Semester", nullable = false)
    private int semester;

    @Column(name = "StudentNumber", nullable = false)
    private int studentNumber;

    @Column(name = "Course", length = 200, nullable = false)
    private String course;

    @Column(name = "TOEIC_Score", nullable = false)
    private int toeicScore;

    @Column(name = "EnglishGrade", nullable = false)
    private int englishGrade;

}
