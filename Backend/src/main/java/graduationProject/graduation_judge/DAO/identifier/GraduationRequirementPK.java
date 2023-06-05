package graduationProject.graduation_judge.DAO.identifier;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class GraduationRequirementPK implements Serializable {
    private static final long serialVersionUID = 3584726848675130643L;
    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    @Column(name = "course", nullable = false, length = 200)
    private String course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GraduationRequirementPK entity = (GraduationRequirementPK) o;
        return Objects.equals(this.enrollmentYear, entity.enrollmentYear) &&
                Objects.equals(this.course, entity.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrollmentYear, course);
    }

    @Builder
    GraduationRequirementPK(Integer enrollmentYear, String course) {
        this.enrollmentYear = enrollmentYear;
        this.course = course;
    }

    public GraduationRequirementPK() {}

}