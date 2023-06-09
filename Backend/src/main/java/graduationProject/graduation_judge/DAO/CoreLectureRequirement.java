package graduationProject.graduation_judge.DAO;

import graduationProject.graduation_judge.DAO.identifier.CoreLectureRequirementPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "core_lecture_requirement")
public class CoreLectureRequirement {
    @EmbeddedId
    private CoreLectureRequirementPK id;


    @Column(name = "category", length = 200)
    private String category;

}