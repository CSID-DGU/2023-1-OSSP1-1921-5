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
@Table(name = "UserSelectList")
public class UserSelectList {


    @Id
    @Column(name = "UserID", nullable = false)
    private String memberId;

    @Id
    @Column(name = "TNumber", nullable = false)
    private String termNum;

    @Id
    @Column(name = "CNumber", nullable = false)
    private String classNum;

    @Column(name = "ClassScore", nullable = false)
    private String score;
}
