package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.domain.Grade.GradeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(GradeId.class)
@Table(name = "user_select_list")
public class UserSelectList {


    @Id
    @Column(name = "user_id", length = 200, nullable = false)
    private String memberId;

    @Id
    @Column(name = "term_number", length = 200, nullable = false)
    private String termNum;

    @Id
    @Column(name = "class_number", length = 200, nullable = false)
    private String classNum;

    @Column(name = "class_score", length = 200, nullable = false)
    private String score;
}
