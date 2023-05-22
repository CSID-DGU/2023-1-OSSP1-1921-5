package graduationProject.graduation_judge.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
