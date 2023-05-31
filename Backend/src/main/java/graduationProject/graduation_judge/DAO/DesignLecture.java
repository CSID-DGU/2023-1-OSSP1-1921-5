package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.DesignLecturePK;
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
@IdClass(DesignLecturePK.class)
@Table(name = "design_lecture")
public class DesignLecture {
    @Id
    @Column(name = "design_term_number", length = 200, nullable = false)
    private String termNum;

    @Id
    @Column(name = "design_class_number", length = 200, nullable = false)
    private String classNum;

    @Column(name = "design_credit")
    private Float designCredit;

    //1:1 EntireLecture
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "design_term_number", referencedColumnName = "termNumber"),
            @JoinColumn(name = "design_class_number", referencedColumnName = "classNumber")
    })
    private EntireLecture des_entireLecture;
}
