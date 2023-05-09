package graduation_spring_test.demo.domain.Grade.service;

import graduation_spring_test.demo.DAO.GradeDAO;
import graduation_spring_test.demo.domain.Grade.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeDAO gradeDao;


    @Override
    public void inputGrade(Grade grade) {
        // 이미 해당 data가 존재하면 삭제후 삽입
        if(gradeDao.isExistGrade(grade)>0) {
            gradeDao.deleteGradeByLec(grade.getClassNum());
        }
        gradeDao.addGrade(grade);
    }

    @Override
    public String getGradeByLec(String cNum) {
        return null;
    }

    @Override
    public void deleteGradeByMember(String memberId) {

    }

    @Override
    public void AllScoreByMember(String memberID) {

    }

    @Override
    public void MajorScoreByMember(String memberId) {

    }
}
