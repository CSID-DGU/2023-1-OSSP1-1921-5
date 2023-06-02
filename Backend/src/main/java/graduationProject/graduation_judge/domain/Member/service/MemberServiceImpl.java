package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DTO.MailDTO;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
import graduationProject.graduation_judge.domain.Grade.repository.GradeRepository;
import graduationProject.graduation_judge.domain.Member.repository.MailRepository;
import graduationProject.graduation_judge.domain.Member.repository.MemberRepository;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class MemberServiceImpl implements MemberService {

    private EmailService emailService;
    private MemberRepository memberRepository;
    private MailRepository mailRepository;
    private GradeRepository gradeRepository;
    private ScoreStatRepository scoreStatRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, EmailService emailService, MailRepository mailRepository, GradeRepository gradeRepository, ScoreStatRepository scoreStatRepository) {
        this.memberRepository = memberRepository;
        this.emailService = emailService;
        this.mailRepository = mailRepository;
        this.gradeRepository = gradeRepository;
        this.scoreStatRepository = scoreStatRepository;
    }

    @Override
    public int emailCheck(String id) {
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        if(userInfo!=null){
            return 1;//email중복일 경우
        }
        else if(userInfo==null){
            return 0;
        }
        return 2;
    }

    @Override
    public void register(UserInfoDTO userInfoDTO) {
        // 회원 가입 로직 구현
        memberRepository.save(userInfoDTO.toEntity());
    }

    @Override
    public String login(String id, String pincode){
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);

        if (userInfo == null){
            return "undefined";
        }
        else if (userInfo.getPincode().equals(pincode)){
            if(userInfo.getUserid().equals(id)){
                return id;
            }
        }
        else{
            return null;
        }
        return "dd";
    }

    @Override
    public UserInfoDTO getMemberById(String id) {
        // 회원 조회 로직 구현
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        if(userInfo == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(),
                userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(),
                userInfo.getToeicScore(),userInfo.getEnglishGrade());
        return userInfoDTO;
    }

    @Override
    public void updateMember(String id, int semester, int studentNumber,
                             Major_curriculum course, int toeicScore,
                             English_level englishGrade) {
        //회원 수정 (id, pincode빼고 수정)
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(),
                userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(),
                userInfo.getToeicScore(),userInfo.getEnglishGrade());

        userInfoDTO.setSemester(semester);
        userInfoDTO.setStudent_number(studentNumber);
        userInfoDTO.setCourse(course);
        userInfoDTO.setToeicScore(toeicScore);
        userInfoDTO.setEnglishGrade(englishGrade);
        memberRepository.save(userInfoDTO.toEntity());
    }

    @Override
    @Transactional
    public void deleteMember(String id) {
        //회원 탈퇴
        memberRepository.deleteAllByUserid(id);//UserInfo 삭제
        mailRepository.deleteAllById(id);//SecurityCodeOfUserMail 삭제
        gradeRepository.deleteAllByMemberId(id);//UserSelectList 삭제
        scoreStatRepository.deleteAllByMemberId(id);//ScoreStat 삭제
    }

    /*@Override
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
    }*/

    @Override
    public void changePassword(String id, String pincode){
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(), userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(), userInfo.getToeicScore(), userInfo.getEnglishGrade());
        userInfoDTO.setPincode(pincode);
        memberRepository.save(userInfoDTO.toEntity());
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
        UserInfo userInfo = memberRepository.findUserInfoByUserid(id);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(), userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(), userInfo.getToeicScore(),userInfo.getEnglishGrade());
        MailDTO mailDTO = new MailDTO(id, null);
        mailDTO.setAddress(id);
        if (userInfoDTO != null) { //id에 해당하는 user가 존재한다면
            String securityCode = generateSecurityCode(); //보안코드 생성
            mailDTO.setMessage(securityCode); //MailDTO에 보안코드 저장
            mailRepository.save(mailDTO.toEntity()); //db에 DTO저장

            String text = "보안 코드: " + securityCode;
            emailService.sendEmail(id, text);
            return securityCode;
        }else{
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
    }

}
