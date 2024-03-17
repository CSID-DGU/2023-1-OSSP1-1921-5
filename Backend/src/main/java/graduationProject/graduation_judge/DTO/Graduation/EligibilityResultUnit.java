package graduationProject.graduation_judge.DTO.Graduation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EligibilityResultUnit {
    private Number criteria;
    private Number value;
    private boolean satisfaction;

    public EligibilityResultUnit(int criteria, int value) {
        this.criteria = criteria;
        this.value = value;
        if(criteria <= value) {
            this.satisfaction = true;
        } else {
            this.satisfaction = false;
        }
    }

    public EligibilityResultUnit(float criteria, float value) {
        this.criteria = criteria;
        this.value = value;
        if(criteria <= value) {
            this.satisfaction = true;
        } else {
            this.satisfaction = false;
        }

    }

}


