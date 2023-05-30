package graduationProject.graduation_judge.domain.Stats;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScoreStatId implements Serializable {
    private String UID;
    private int semester;
    private String typeId;
}
