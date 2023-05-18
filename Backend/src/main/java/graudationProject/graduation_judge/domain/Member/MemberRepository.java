package graudationProject.graduation_judge.domain.Member;

import java.util.List;
import java.util.Optional;

public class MemberRepository {
    Member save(Member member); //회원 정보를 저장
    Optional<Member> findById(String id); //아이디를 이용해 회원 정보 조회
    Optional<Member> findByPassword(String password); //비밀번호를 이용해 회원 정보 조회
    List<Member> findAll(); //모든 회원 정보 조회
    void update(Member member); //회원 정보 수정
    void delete(Member member); //회원 정보 삭제
}
