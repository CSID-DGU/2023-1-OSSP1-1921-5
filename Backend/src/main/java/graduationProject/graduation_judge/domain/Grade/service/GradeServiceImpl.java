package graduationProject.graduation_judge.domain.Grade.service;

import graduationProject.graduation_judge.DAO.GradeDAO;
import graduationProject.graduation_judge.domain.Grade.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeDAO gradeDao;


    @Override
    public void inputGrade(Grade grade) {

    }

    @Override
    public String getGradeByLec(String cNum) {
        return null;
    }

    @Override
    public void deleteGradeByMember(String memberId) {

    }

    @Override
    public float getAllScoreByMember(String memberID) {
        return 0;
    }

    @Override
    public float getMajorScoreByMember(String memberId) {
        return 0;
    }

    @Override
    public float getEntireAllScore() {
        return 0;
    }

    @Override
    public float getEntireMajorScore() {
        return 0;
    }
}
