package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.UserSelectListPK;
import graduationProject.graduation_judge.domain.Grade.GradeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(UserSelectListPK.class)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "term_number", referencedColumnName = "term_number"),
            @JoinColumn(name = "class_number", referencedColumnName = "class_number")
    })
    @ToString.Exclude
    private EntireLecture entireLecture;

    public UserSelectList(String memberId, String termNum, String classNum, String score) {
        this.memberId = memberId;
        this.termNum = termNum;
        this.classNum = classNum;
        this.score = score;
    }
}
