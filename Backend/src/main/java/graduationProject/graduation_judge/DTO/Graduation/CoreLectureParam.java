package graduationProject.graduation_judge.DTO.Graduation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CoreLectureParam {

    /**
     * <h3>이수하지 않은 필수 강의 리스트</h3>
     * notTakingNC: 미이수 공통교양
     * notTakingBSM: 미이수 학문교양 리스트
     * notTakingMJ: 미이수 전공 리스트
     */

    //용도모르겠음
    private String name;
    private List<String> notTakingNC;
    private List<String> notTakingBSM;
    private List<String> notTakingMJ;

    @Builder
    public CoreLectureParam(
            List<String> notTakingNC,
            List<String> notTakingBSM,
            List<String> notTakingMJ
    ) {
        this.notTakingNC = notTakingNC;
        this.notTakingBSM = notTakingBSM;
        this.notTakingMJ = notTakingMJ;
    };

    public CoreLectureParam() {

    }

}