package Member;

import graduationProject.graduation_judge.domain.Member.Member;
import graduationProject.graduation_judge.domain.Member.service.MemberService;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;
    @Test
    void join() {
        //given
        Member member = new Member("user201712@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, English_level.S3,115,null);
        //when
        memberService.register(member);
        Member findMember = memberService.getMemberById("user201712@gmail.com");
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
