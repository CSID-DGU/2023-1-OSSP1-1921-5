package graduationProject.graduation_judge.DTO.Graduation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
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


}