package graduationProject.graduation_judge.domain.Grade;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GradeId implements Serializable {

    private String memberId;

    private String termNum;

    private String classNum;

}

