package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
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
    public void register(UserInfoDTO userInfoDTO) {
        // 회원 가입 로직 구현
        //이메일 중복 확인 하기
        if (memberRepository.findById(userInfoDTO.getId()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        memberRepository.save(toEntity(userInfoDTO));
    }

    @Override
    public UserInfo getMemberById(String id) {
        // 회원 조회 로직 구현
        if(memberRepository.findById(id) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        return memberRepository.findById(id);
    }

    @Override
    public void updateMember(UserInfoDTO userInfoDTO) {
        //회원 수정
        memberRepository.save(toEntity(userInfoDTO));
    }

    @Override
    public void deleteMember(UserInfoDTO userInfoDTO) {
        //회원 삭제
        memberRepository.delete(toEntity(userInfoDTO));
    }

    @Override
    public void findPassword(String id, String securityCode, String inputSecurityCode, String newPassword) {
        //보안코드 확인 후 비밀번호 변경
        UserInfo userInfo = memberRepository.findById(id);
        if (userInfo != null && inputSecurityCode.equals(securityCode)) {
            userInfo.setPincode(newPassword);
            memberRepository.save(userInfo);
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
    public String sendSecurityCodeToEmail(String id){
        UserInfo userInfo = memberRepository.findById(id);
        if (userInfo != null) {
            String securityCode = generateSecurityCode();
            memberRepository.save(userInfo);
            String subject = "비밀번호를 찾기 위한 보안 코드입니다.";
            String text = "보안 코드: " + securityCode;
            emailService.sendEmail(id, subject, text);
            return securityCode;
        }else{
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
    }

    @Override
    public UserInfo toEntity(UserInfoDTO userInfoDTO) {
        // UserInfoDTO to Entity
        return new UserInfo(userInfoDTO.getId(),
                userInfoDTO.getPincode(),
                userInfoDTO.getSemester(),
                userInfoDTO.getStudentNumber(),
                userInfoDTO.getCourse(),
                userInfoDTO.getToeicScore(),
                userInfoDTO.getEnglishGrade());
    }
}
