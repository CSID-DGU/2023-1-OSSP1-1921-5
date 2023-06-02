package graduationProject.graduation_judge.DTO.Graduation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CoreLectureParam {

    /**
     * <h3>이수하지 않은 필수 강의 리스트</h3>
     * notTakingNC: 미이수 공통교양
     * notTakingBSM: 미이수 학문교양 리스트
     * notTakingMJ: 미이수 전공 리스트
     */

    private List<String> notTakingNC;
    private List<String> notTakingBSM;
    private List<String> notTakingMJ;

}