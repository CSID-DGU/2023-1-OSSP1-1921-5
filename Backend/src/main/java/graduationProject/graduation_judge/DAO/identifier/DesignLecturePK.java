package graduationProject.graduation_judge.DAO.identifier;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DesignLecturePK implements Serializable {
    private String termNum;
    private String classNum;
}

