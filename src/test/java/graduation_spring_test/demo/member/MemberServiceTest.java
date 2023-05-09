package graduation_spring_test.demo.member;

import graduation_spring_test.demo.DAO.MemberDao;
import graduation_spring_test.demo.domain.Member.Member;
import graduation_spring_test.demo.domain.Member.service.MemberService;
import graduation_spring_test.demo.global.common_unit.English_level;
import graduation_spring_test.demo.global.common_unit.Major_curriculum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;

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
