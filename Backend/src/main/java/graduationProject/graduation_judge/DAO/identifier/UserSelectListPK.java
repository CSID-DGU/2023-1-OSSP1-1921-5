package graduationProject.graduation_judge.DAO.identifier;

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
public class UserSelectListPK implements Serializable {
    private String memberId;
    private String classNum;
    private String termNum;
}

