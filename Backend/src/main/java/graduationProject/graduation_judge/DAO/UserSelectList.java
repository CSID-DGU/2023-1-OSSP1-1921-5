package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.UserSelectListPK;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(UserSelectListPK.class)
@Table(name = "user_select_list")
public class UserSelectList {
    @Id
    @Column(name = "user_id", insertable=false, updatable=false)
    private String memberId;

    @Id
    @Column(name = "class_number", insertable = false, updatable = false)  // 일치하는 필드 추가
    private String classNum;

    @Id
    @Column(name = "term_number", insertable = false, updatable = false)    // 일치하는 필드 추가
    private String termNum;

    //M:1 EntireLecture
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "term_number", referencedColumnName = "term_number"),
            @JoinColumn(name = "class_number", referencedColumnName = "class_number")
    })
    @ToString.Exclude
    private EntireLecture us_entireLecture;


    //M:1 UserInfo
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user_id;

    @Column(name = "class_score", length = 200, nullable = false)
    private String score;

    @Builder
    public UserSelectList(String memberId, String classNum, String termNum, String score){
        this.memberId = memberId;
        this.classNum = classNum;
        this.termNum = termNum;
        this.score = score;
    }

}
