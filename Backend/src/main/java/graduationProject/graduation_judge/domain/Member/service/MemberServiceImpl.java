package graduationProject.graduation_judge.domain.Member.service;


import graduationProject.graduation_judge.DAO.Member;
import graduationProject.graduation_judge.domain.Member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class MemberServiceImpl implements MemberService {

    private final EmailService emailService;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, EmailService emailService) {
        this.memberRepository = memberRepository;
        this.emailService = emailService;
    }

    @Override
    public void register(Member member) {
        // 회원 가입 로직 구현
        //이메일 중복 확인 하기
        if (memberRepository.findById(member.getId()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        memberRepository.save(member);
    }

    @Override
    public Member getMemberById(String memberId) {
        // 회원 조회 로직 구현
        if(memberRepository.findById(memberId) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        return memberRepository.findById(memberId);
    }

    @Override
    public void updateMember(Member member) {
        //회원 수정
        memberRepository.save(member);
    }

    @Override
    public void deleteMember(Member member) {
        //회원 삭제
        memberRepository.delete(member);
    }

    @Override
    public void findPassword(String memberId, String securityCode, String newPassword) {
        //보안코드 확인 후 비밀번호 변경
        Member member = memberRepository.findById(memberId);
        if (member != null && member.getSecurity_code().equals(securityCode)) {
            member.setPassword(newPassword);
            memberRepository.save(member);
        } else {
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
        Member member = memberRepository.findById(memberId);
        if (member != null) {
            String securityCode = generateSecurityCode();
            member.setSecurity_code(securityCode);
            memberRepository.save(member);
            String subject = "비밀번호를 찾기 위한 보안 코드입니다.";
            String text = "보안 코드: " + securityCode;
            emailService.sendEmail(memberId, subject, text);
        }else{
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
    }
}
