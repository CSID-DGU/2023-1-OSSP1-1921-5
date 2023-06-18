package graduationProject.graduation_judge.DAO.identifier;

import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CoreLectureRequirementPK implements Serializable {
    private static final long serialVersionUID = 4946439625948576799L;
    @Column(name = "lecture_number", nullable = false, length = 200)
    private String lectureNumber;

    @Column(name = "lecture_name", nullable = false, length = 200)
    private String lectureName;

    @Column(name = "course", nullable = false, length = 200)
    private String course;

    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    public CoreLectureRequirementPK(Integer enrollment, Major_curriculum course, String lectureName, String lecutreNumber) {
        this.enrollmentYear = enrollment;
        this.course = String.valueOf(course);
        this.lectureName = lecutreNumber;
        this.lectureNumber = lecutreNumber;
    }

    public CoreLectureRequirementPK() {

    }

    public CoreLectureRequirementPK(String prevlectureNumber, String lectureName, Major_curriculum curriculum, int enrollment) {
    this.lectureNumber = prevlectureNumber;
    this.lectureName = lectureName;
    this.enrollmentYear = enrollment;
    this.course = String.valueOf(curriculum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CoreLectureRequirementPK entity = (CoreLectureRequirementPK) o;
        return Objects.equals(this.lectureNumber, entity.lectureNumber) &&
                Objects.equals(this.lectureName, entity.lectureName) &&
                Objects.equals(this.enrollmentYear, entity.enrollmentYear) &&
                Objects.equals(this.course, entity.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lectureNumber, lectureName, enrollmentYear, course);
    }

    public String getLecture_number() {
        return this.lectureNumber;
    }

    public void setLecture_number(String lectureNumber) {
        this.lectureNumber = lectureNumber;
    }
}