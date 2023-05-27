package graduationProject.graduation_judge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
//특정 도메인 컴포넌트 스캔 제외
//@ComponentScan(basePackages = {
//		"graduationProject.graduation_judge",
////		"other.package.to.include"
//}, excludeFilters = {
//		@ComponentScan.Filter(
//				type = FilterType.REGEX,
//				pattern = "graduationProject.graduation_judge.domain.*"
//		),
//})
public class GraduationJudgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduationJudgeApplication.class, args);
	}

}