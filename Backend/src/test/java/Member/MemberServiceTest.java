package Member;


import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.domain.Member.repository.MemberRepository;
import graduationProject.graduation_judge.domain.Member.service.EmailService;
import graduationProject.graduation_judge.domain.Member.service.MemberService;
import graduationProject.graduation_judge.domain.Member.service.MemberServiceImpl;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class MemberServiceTest {

    MemberService memberService;
    @Mock
    MemberRepository memberRepository;
    EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
        memberService = new MemberServiceImpl(memberRepository, emailService);
    }

    @Test
    void join() {
        //given
        UserInfo userInfo = new UserInfo("user20171@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, 115, English_level.S3);
        //when
        //doReturn(member).when(memberRepository).findById(member.getId());
        memberService.register(userInfo);
        UserInfo findMember = memberRepository.findById(userInfo.getId());
        //then
        //Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember = " + findMember.getId());
        System.out.println("member = " + userInfo.getId());
    }
}
