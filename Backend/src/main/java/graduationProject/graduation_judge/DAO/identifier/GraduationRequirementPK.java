package graduationProject.graduation_judge.DAO.identifier;

import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
import lombok.Builder;
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
public class GraduationRequirementPK implements Serializable {
    private static final long serialVersionUID = 3584726848675130643L;
    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "course", nullable = false, length = 200)
    private Major_curriculum course;

    public GraduationRequirementPK(int enrollment, Major_curriculum curriculum) {
        this.enrollmentYear = enrollment;
        this.course = curriculum;
    }

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
    public GraduationRequirementPK(Integer enrollmentYear, Major_curriculum course) {
        this.enrollmentYear = enrollmentYear;
        this.course = course;
    }

    public GraduationRequirementPK() {}

}