package graduation_spring_test.demo.domain.Member.service;

import graduation_spring_test.demo.DAO.MemberDao;
import graduation_spring_test.demo.domain.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public void register(Member member) {
        // 회원 가입 로직 구현
        memberDao.addMember(member);
    }

    @Override
    public Member getMemberById(String memberId) {
        // 회원 조회 로직 구현
        return memberDao.getMemberById(memberId);
    }

    @Override
    public void updateMember() {

    }

    @Override
    public void findPassword() {

    }

    // ...
}
