package graduationProject.graduation_judge.DTO.Graduation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Eligibility_Result_Unit {
    private Number criteria;
    private Number value;
    private boolean satisfaction;

    public Eligibility_Result_Unit(int criteria, int value) {
        this.criteria = criteria;
        this.value = value;
        if(criteria <= value) {
            this.satisfaction = true;
        } else {
            this.satisfaction = false;
        }
    }

    public Eligibility_Result_Unit(float criteria, float value) {
        this.criteria = criteria;
        this.value = value;
        if(criteria <= value) {
            this.satisfaction = true;
        } else {
            this.satisfaction = false;
        }

    }

}


