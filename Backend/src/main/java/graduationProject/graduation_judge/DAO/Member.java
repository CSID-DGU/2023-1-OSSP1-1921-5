package graduationProject.graduation_judge.DAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // JPA에서 lazy관련 에러 날 경우 사용
@Table(name = "userinfo")
public final class Member {
    @Id
    @Column(name = "id", nullable = false)
    private String id; //아이디
    @Column(name = "password", nullable = false)
    private String password; //비밀번호
    @Column(name = "enroll_year", nullable = false)
    private Integer enroll_year; //입학년도
    @Column(name = "completed_semesters", nullable = false)
    private Integer completed_semesters; //학기
    @Column(name = "major_curriculum", nullable = false)
    private Major_curriculum major_curriculum; //심화 or 일반
    @Column(name = "eng_level", nullable = false)
    private English_level eng_level; //S0, S1..
    @Column(name = "TOEIC_score", nullable = false)
    private Integer TOEIC_score; //토익 점수
    @Column(name = "security_code")
    private String  security_code; //비밀번호 변경시 보안코드
}