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
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "user_id", length = 200, nullable = false)
    private String id;

    @Column(name = "pincode", length = 200, nullable = false)
    private String pincode;

    @Column(name = "semester", nullable = false)
    private int semester;

    @Column(name = "student_number", nullable = false)
    private int studentNumber;

    @Column(name = "course", length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private Major_curriculum course;

    @Column(name = "toeic_score", nullable = false)
    private int toeicScore;

    @Column(name = "english_grade", length = 200, nullable = false, columnDefinition = "tinyint")
    @Enumerated(EnumType.STRING)
    private English_level englishGrade;

}
