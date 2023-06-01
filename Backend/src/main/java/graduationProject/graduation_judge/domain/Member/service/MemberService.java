package graduationProject.graduation_judge.domain.Member.service;

import graduationProject.graduation_judge.DAO.UserInfo;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    int emailCheck(String id); //이메일확인
    void register(UserInfoDTO userInfoDTO);  // 회원 가입
    void login(String id, String pincode);    //로그인

    UserInfoDTO getMemberById(String id);  // 회원 조회

    void updateMember(String id, int semester, int studentNumber,
                      Major_curriculum course, int toeicScore,
                      English_level englishGrade); // 회원 수정

    void findPassword(String id, String inputSecurityCode, String newPassword); //비밀번호 찾기

    void deleteMember(UserInfoDTO userInfoDTO); // 회원 탈퇴(삭제)

    //보안 코드를 이메일로 전송하는 메서드
    void sendSecurityCodeToEmail(String id);

}
