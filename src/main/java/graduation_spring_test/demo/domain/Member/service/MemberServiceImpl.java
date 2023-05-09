package graduation_spring_test.demo.domain.Member.service;

import graduation_spring_test.demo.DAO.MemberDao;
import graduation_spring_test.demo.domain.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class MemberServiceImpl implements MemberService {


    private MemberDao memberDao;
    private EmailService emailService;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao, EmailService emailService) {
        this.memberDao = memberDao;
        this.emailService = emailService;
    }

    @Override
    public void register(Member member) {
        // 회원 가입 로직 구현
        //이메일 중복 확인 하기
        if (memberDao.getMemberById(member.getId()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        memberDao.addMember(member);
    }

    @Override
    public Member getMemberById(String memberId) {
        // 회원 조회 로직 구현
        if(memberDao.getMemberById(memberId) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        return memberDao.getMemberById(memberId);
    }

    @Override
    public void updateMember(Member member) {
        //회원 수정
        memberDao.updateMember(member);
    }

    @Override
    public void deleteMember(Member member) {
        //회원 삭제
        memberDao.deleteMember(member);
    }

    @Override
    public void findPassword(String memberId, String securityCode, String newPassword) {
        //보안코드 확인 후 비밀번호 변경
        Member member = memberDao.getMemberById(memberId);
        if (member != null && member.getSecurity_code().equals(securityCode)) {
            member.setPassword(newPassword);
            memberDao.updateMember(member);
        }else{
            throw new RuntimeException("올바르지 않은 보안 코드입니다.");
        }

    }

    //보안 코드 생성 메서드
    private String generateSecurityCode() {
        Random random = new Random();
        int code = random.nextInt(10000);
        return String.format("%04d", code);
    }

    //보안 코드를 이메일로 전송하는 메서드
    @Override
    public void sendSecurityCodeToEmail(String memberId){
        Member member = memberDao.getMemberById(memberId);
        if (member != null) {
            String securityCode = generateSecurityCode();
            member.setSecurity_code(securityCode);
            memberDao.updateMember(member);
            String subject = "비밀번호를 찾기 위한 보안 코드입니다.";
            String text = "보안 코드: " + securityCode;
            emailService.sendEmail(memberId, subject, text);
        }else{
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
    }
}
