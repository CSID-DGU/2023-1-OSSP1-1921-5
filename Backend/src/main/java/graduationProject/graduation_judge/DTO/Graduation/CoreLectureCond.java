package graduationProject.graduation_judge.DTO.Graduation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoreLectureCond {

    /**
     * <h3>필수 강의 조건</h3>
     * major: 전공 리스트
     * common_edu: 공통교양 리스트
     * general_edu: 학문교양 리스트
     */

    private List<String> major;
    private List<String> common_edu;
    private List<String> general_edu;

    @Builder
    public CoreLectureCond(
            List<String> major,
            List<String> common_edu,
            List<String> general_edu
    ) {
        this.major = major;
        this.common_edu = common_edu;
        this.general_edu = general_edu;
    }

    public CoreLectureCond () {

    };
}