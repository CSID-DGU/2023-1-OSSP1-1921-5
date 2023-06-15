package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DTO.Member.EmailCheck.GetEmailDTO;
import graduationProject.graduation_judge.DTO.Member.MailDTO;
import graduationProject.graduation_judge.DTO.Member.MyPage.SendMyPageInfoDTO;
import graduationProject.graduation_judge.DTO.Member.SendEmail.SendEmailCodeDTO;
import graduationProject.graduation_judge.DTO.Member.ShowUserInfo.SendUserInfoDTO;
import graduationProject.graduation_judge.DTO.Member.ShowUserInfo.SendUserInfoListDTO;
import graduationProject.graduation_judge.DTO.Member.SignIn.GetSignInDTO;
import graduationProject.graduation_judge.DTO.Member.SignIn.SendSignInCheckDTO;
import graduationProject.graduation_judge.DTO.Member.SignUp.GetSignUpDTO;
import graduationProject.graduation_judge.DTO.Member.Update.GetUpdateInfoDTO;
import graduationProject.graduation_judge.DTO.Member.UserInfoDTO;
import graduationProject.graduation_judge.domain.Grade.repository.GradeRepository;
import graduationProject.graduation_judge.domain.Member.repository.MailRepository;
import graduationProject.graduation_judge.domain.Member.repository.MemberRepository;
import graduationProject.graduation_judge.domain.Stats.repository.ScoreStatRepository;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public void register(GetSignUpDTO getSignUpDTO) {
        // 회원 가입 로직 구현
        UserInfoDTO userInfoDTO = new UserInfoDTO(
                getSignUpDTO.getEmail(),
                getSignUpDTO.getPw(),
                Integer.parseInt(getSignUpDTO.getSemester()),
                Integer.parseInt(getSignUpDTO.getYear()),
                Major_curriculum.valueOf(getSignUpDTO.getCourse()),
                Integer.parseInt(getSignUpDTO.getScore()),
                English_level.valueOf(getSignUpDTO.getEnglish())
        );
        memberRepository.save(userInfoDTO.toEntity());
    }

    @Override
    public SendSignInCheckDTO login(GetSignInDTO getSignInDTO){
        UserInfo userInfo = memberRepository.findUserInfoByUserid(getSignInDTO.getEmail());
        SendSignInCheckDTO sendSignInCheckDTO = new SendSignInCheckDTO("dd");
        if (userInfo == null){
            sendSignInCheckDTO.setId("undefined");
            return sendSignInCheckDTO;
        }
        else if (userInfo.getPincode().equals(getSignInDTO.getPw())){
            if(userInfo.getUserid().equals(getSignInDTO.getEmail())){
                sendSignInCheckDTO.setId(getSignInDTO.getEmail());
                return sendSignInCheckDTO;//원래 아이디 담아서 보내기
            }
        }
        else{
            sendSignInCheckDTO.setId(null);
            return sendSignInCheckDTO;
        }
        return sendSignInCheckDTO;
    }

    @Override
    public UserInfoDTO getMemberById(String id) {
        // 회원 조회 로직 구현 - UserInfoDTO반환
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
    public SendMyPageInfoDTO getMyPageInfoById(GetEmailDTO getEmailDTO) {
        UserInfo userInfo = memberRepository.findUserInfoByUserid(getEmailDTO.getEmail());
        if(userInfo == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        SendMyPageInfoDTO sendMyPageInfoDTO = new SendMyPageInfoDTO(
                String.valueOf(userInfo.getStudent_number()),
                String.valueOf(userInfo.getSemester()),
                String.valueOf(userInfo.getCourse()),
                String.valueOf(userInfo.getEnglishGrade()),
                String.valueOf(userInfo.getToeicScore())
        );
        return sendMyPageInfoDTO;
    }

    @Override
    public SendUserInfoDTO getUserInfoDTOById(GetEmailDTO getEmailDTO) {
        if(getEmailDTO.getEmail() == null){
            System.out.println("id is null");
            throw new IllegalArgumentException("아이디가 null");
        }
        UserInfo userInfo = memberRepository.findUserInfoByUserid(getEmailDTO.getEmail());
        if(userInfo == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        SendUserInfoDTO sendUserInfoDTO = new SendUserInfoDTO(
                userInfo.getUserid(),
                userInfo.getPincode(),
                String.valueOf(userInfo.getStudent_number()),
                String.valueOf(userInfo.getSemester())
        );
        return sendUserInfoDTO;
    }


    @Override
    public void updateMember(GetUpdateInfoDTO getUpdateInfoDTO) {
        //회원 수정 (id, pincode빼고 수정)
        UserInfo userInfo = memberRepository.findUserInfoByUserid(getUpdateInfoDTO.getEmail());
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(),
                userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(),
                userInfo.getToeicScore(),userInfo.getEnglishGrade());

        userInfoDTO.setSemester(Integer.parseInt(getUpdateInfoDTO.getRegister()));
        userInfoDTO.setStudent_number(Integer.parseInt(getUpdateInfoDTO.getYear()));
        userInfoDTO.setCourse(Major_curriculum.valueOf(getUpdateInfoDTO.getCourse()));
        userInfoDTO.setToeicScore(Integer.parseInt(getUpdateInfoDTO.getScore()));
        userInfoDTO.setEnglishGrade(English_level.valueOf(getUpdateInfoDTO.getEnglish()));
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

    @Override
    public void changePassword(GetSignInDTO getSignInDTO){
        UserInfo userInfo = memberRepository.findUserInfoByUserid(getSignInDTO.getEmail());
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.getUserid(), userInfo.getPincode(), userInfo.getSemester(),
                userInfo.getStudent_number(), userInfo.getCourse(), userInfo.getToeicScore(), userInfo.getEnglishGrade());
        userInfoDTO.setPincode(getSignInDTO.getPw());
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
    public SendEmailCodeDTO sendSecurityCodeToEmail(String id){
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

            SendEmailCodeDTO sendEmailCodeDTO = new SendEmailCodeDTO(securityCode);
            return sendEmailCodeDTO;
        }else{
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
    }

    @Override
    public SendUserInfoListDTO showAllUser() {
        List<UserInfo> userInfos = memberRepository.findAll();
        List<SendUserInfoDTO> userInfoDTOS = new ArrayList<>(); //빈 리스트 생성
        for (UserInfo userInfo : userInfos){
            SendUserInfoDTO sendUserInfoDTO = new SendUserInfoDTO(
                    userInfo.getUserid(),
                    userInfo.getPincode(),
                    String.valueOf(userInfo.getStudent_number()),
                    String.valueOf(userInfo.getSemester())
            );//id, pw, studentNumber, semester저장
            userInfoDTOS.add(sendUserInfoDTO);
        }
        SendUserInfoListDTO sendUserInfoListDTO = new SendUserInfoListDTO(userInfoDTOS);
        return sendUserInfoListDTO;
    }

}
