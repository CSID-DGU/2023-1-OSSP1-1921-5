package graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetLectureInfoListDTO {
    private String ClassNumber;
    private String ProfessorName;
    private String LectureNick;
    private String Curriculum;
    private String ClassArea;
    private String ClassCredit;
    private String DesignCredit;
    private String IsEnglish;
    @Override
    public String toString() {
        return "ClassNumber="+ClassNumber+
                ", ProfessorName="+ProfessorName+
                ", LectureNick="+LectureNick+
                ", Curriculum="+Curriculum
                +", ClassArea="+ClassArea+
                ", ClassCredit="+ClassCredit+
                ", DesignCredit="+DesignCredit+
                ", IsEnghlish="+IsEnglish;
    }
}