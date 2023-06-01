package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.UserInfo;
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
    public int emailCheck(String id) {
        if(memberRepository.findUserInfoByUserid(id) != null){
            return 1;//email중복일 경우
        }
        else return 0;
    }

    @Override
    public void register(UserInfoDTO userInfoDTO) {
        // 회원 가입 로직 구현
        //이메일 중복 확인 하기
        /*if (memberRepository.findUserInfoByUserid(userInfoDTO.getUserid()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }*/
        memberRepository.save(userInfoDTO.toEntity());
    }

    @Override
    public String login(String id, String pincode){
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);

        if (userInfo == null){
            return "undefined";
        }
        else if (userInfo.getPincode() != pincode){
            return null;
        } else if (userInfo.getUserid() == id && userInfo.getPincode() == pincode) {
            return id;
        }
        return "dd";
    }

    @Override
    public UserInfoDTO getMemberById(String id) {
        // 회원 조회 로직 구현
        if(memberRepository.findUserInfoByUserid(id) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(), userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(), userInfo.getToeicScore(),userInfo.getEnglishGrade());
        return userInfoDTO;
    }

    @Override
    public void updateMember(String id, int semester, int studentNumber,
                             Major_curriculum course, int toeicScore,
                             English_level englishGrade) {
        //회원 수정 (id, pincode빼고 수정)
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(), userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(), userInfo.getToeicScore(),userInfo.getEnglishGrade());

        userInfoDTO.setSemester(semester);
        userInfoDTO.setStudent_number(studentNumber);
        userInfoDTO.setCourse(course);
        userInfoDTO.setToeicScore(toeicScore);
        userInfoDTO.setEnglishGrade(englishGrade);
        memberRepository.save(userInfo);
    }

    @Override
    public void deleteMember(UserInfoDTO userInfoDTO) {
        //회원 삭제
        memberRepository.delete(userInfoDTO.toEntity());
    }

    @Override
    public void findPassword(String id, String inputSecurityCode, String newPassword) {
        //보안코드 확인 후 비밀번호 변경
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(), userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(), userInfo.getToeicScore(),userInfo.getEnglishGrade());
        MailDTO mailDTO = mailRepository.findById(id);
        if (userInfoDTO != null && inputSecurityCode.equals(mailDTO.getMessage())) {
            userInfoDTO.setPincode(newPassword);
            memberRepository.save(userInfoDTO.toEntity());
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
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(), userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(), userInfo.getToeicScore(),userInfo.getEnglishGrade());
        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(id);
        if (userInfoDTO != null) { //id에 해당하는 user가 존재한다면
            String securityCode = generateSecurityCode(); //보안코드 생성
            mailDTO.setMessage(securityCode); //MailDTO에 보안코드 저장
            mailRepository.save(mailDTO.toEntity()); //db에 DTO저장
            //memberRepository.save(toEntity(userInfoDTO)); //save왜하냐?
            String text = "보안 코드: " + securityCode;
            emailService.sendEmail(id, text);
        }else{
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
    }

}
