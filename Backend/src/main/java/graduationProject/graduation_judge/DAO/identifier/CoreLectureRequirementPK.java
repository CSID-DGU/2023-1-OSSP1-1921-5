package graduationProject.graduation_judge.DAO.identifier;

import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CoreLectureRequirementPK implements Serializable {
    private static final long serialVersionUID = -5845020741253606702L;
    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    @Column(name = "course", nullable = false, length = 200)
    @Enumerated(EnumType.STRING)
    private Major_curriculum course;

    @Column(name = "lecture_name", nullable = false, length = 200)
    private String lectureName;

    public CoreLectureRequirementPK(Integer enrollment, Major_curriculum course, String lectureName) {
    }

    public CoreLectureRequirementPK() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CoreLectureRequirementPK entity = (CoreLectureRequirementPK) o;
        return Objects.equals(this.lectureName, entity.lectureName) &&
                Objects.equals(this.enrollmentYear, entity.enrollmentYear) &&
                Objects.equals(this.course, entity.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lectureName, enrollmentYear, course);
    }

}