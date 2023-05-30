package graduationProject.graduation_judge.domain.Lecture;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LectureId implements Serializable {
    private String termNum;
    private String classNum;
}
