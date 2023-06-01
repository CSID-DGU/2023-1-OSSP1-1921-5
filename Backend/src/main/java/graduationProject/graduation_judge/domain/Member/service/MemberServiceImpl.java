package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.DTO.MailDTO;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
import graduationProject.graduation_judge.domain.Member.repository.MailRepository;
import graduationProject.graduation_judge.domain.Member.repository.MemberRepository;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
@Service
public class MemberServiceImpl implements MemberService {

    private final EmailService emailService;
    private final MemberRepository memberRepository;
    private final MailRepository mailRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, EmailService emailService, MailRepository mailRepository) {
        this.memberRepository = memberRepository;
        this.emailService = emailService;
        this.mailRepository = mailRepository;
    }

    @Override
    public void register(UserInfoDTO userInfoDTO) {
        // 회원 가입 로직 구현
        //이메일 중복 확인 하기
        if (memberRepository.findUserInfoByUser_id(userInfoDTO.getId()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        memberRepository.save(toEntity(userInfoDTO));
    }

    @Override
    public void login(String id, String pincode){
        UserInfoDTO userInfoDTO = memberRepository.findUserInfoByUser_id(id);
        if (userInfoDTO == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        else if (userInfoDTO.getPincode() != pincode){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public UserInfoDTO getMemberById(String id) {
        // 회원 조회 로직 구현
        if(memberRepository.findUserInfoByUser_id(id) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        return memberRepository.findUserInfoByUser_id(id);
    }

    @Override
    public void updateMember(String id, int semester, int studentNumber,
                             Major_curriculum course, int toeicScore,
                             English_level englishGrade) {
        //회원 수정 (id, pincode빼고 수정)
        UserInfoDTO userInfoDTO = memberRepository.findUserInfoByUser_id(id);
        userInfoDTO.setSemester(semester);
        userInfoDTO.setStudentNumber(studentNumber);
        userInfoDTO.setCourse(course);
        userInfoDTO.setToeicScore(toeicScore);
        userInfoDTO.setEnglishGrade(englishGrade);
        memberRepository.save(toEntity(userInfoDTO));
    }

    @Override
    public void deleteMember(UserInfoDTO userInfoDTO) {
        //회원 삭제
        memberRepository.delete(toEntity(userInfoDTO));
    }

    @Override
    public void findPassword(String id, String inputSecurityCode, String newPassword) {
        //보안코드 확인 후 비밀번호 변경
        UserInfoDTO userInfoDTO = memberRepository.findUserInfoByUser_id(id);
        MailDTO mailDTO = mailRepository.findById(id);
        if (userInfoDTO != null && inputSecurityCode.equals(mailDTO.getMessage())) {
            userInfoDTO.setPincode(newPassword);
            memberRepository.save(toEntity(userInfoDTO));
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
    public void sendSecurityCodeToEmail(String id){
        UserInfoDTO userInfoDTO = memberRepository.findUserInfoByUser_id(id);
        //MailDTO mailDTO = mailRepository.findById(id);
        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(id);
        if (userInfoDTO != null) { //id에 해당하는 user가 존재한다면
            String securityCode = generateSecurityCode(); //보안코드 생성
            mailDTO.setMessage(securityCode); //MailDTO에 보안코드 저장
            mailRepository.save(emailService.toEntity(mailDTO)); //db에 DTO저장
            //memberRepository.save(toEntity(userInfoDTO)); //save왜하냐?
            String text = "보안 코드: " + securityCode;
            emailService.sendEmail(id, text);
        }else{
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
    }

    @Override
    public UserInfo toEntity(UserInfoDTO userInfoDTO) {
        // UserInfoDTO to Entity
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_id(userInfoDTO.getId());
        userInfo.setPincode(userInfoDTO.getPincode());
        userInfo.setSemester(userInfoDTO.getSemester());
        userInfo.setStudent_number(userInfoDTO.getStudentNumber());
        userInfo.setCourse(userInfoDTO.getCourse());
        userInfo.setToeicScore(userInfoDTO.getToeicScore());
        userInfo.setEnglishGrade(userInfoDTO.getEnglishGrade());
        return userInfo;
    }
}
