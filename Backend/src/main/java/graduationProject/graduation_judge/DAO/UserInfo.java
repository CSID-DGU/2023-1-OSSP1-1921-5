package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    private Major_curriculum course;

    @Column(name = "TOEIC_Score", nullable = false)
    private int toeicScore;

    @Column(name = "EnglishGrade", nullable = false)
    private English_level englishGrade;

}
