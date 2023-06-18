package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.UserSelectListPK;
//import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(UserSelectListPK.class)
@Table(name = "user_select_list")
public class UserSelectList {
    @Id
    @Column(name = "user_id")
    private String memberId;

    @Id
    @Column(name = "class_number")  // 일치하는 필드 추가
    private String classNum;

    @Id
    @Column(name = "term_number")    // 일치하는 필드 추가
    private String termNum;

    @Column(name = "class_score", length = 200, nullable = false)
    private String score;

    //M:1 EntireLecture
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "term_number", referencedColumnName = "term_number", insertable=false, updatable=false),
            @JoinColumn(name = "class_number", referencedColumnName = "class_number", insertable=false, updatable=false)
    })
    @ToString.Exclude
    private EntireLecture entireLecture;


    //M:1 UserInfo
    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private UserInfo userid;

    @Builder
    public UserSelectList(String memberId, String classNum, String termNum, String score){
        this.memberId = memberId;
        this.classNum = classNum;
        this.termNum = termNum;
        this.score = score;
    }

}
