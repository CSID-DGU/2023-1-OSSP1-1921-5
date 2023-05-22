package graduationProject.graduation_judge.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "design_lecture")
public class DesignLecture {
    @Id
    @Column(name = "Design_TermNumber", length = 200, nullable = false)
    private String termNum;

    @Id
    @Column(name = "Design_ClassNumber", length = 200, nullable = false)
    private String classNum;

    @Column(name = "DesignCredit")
    private Float designCredit;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "termNum", referencedColumnName = "TermNumber"),
            @JoinColumn(name = "classNum", referencedColumnName = "ClassNumber")
    })
    private EntireLecture entireLecture;
}
