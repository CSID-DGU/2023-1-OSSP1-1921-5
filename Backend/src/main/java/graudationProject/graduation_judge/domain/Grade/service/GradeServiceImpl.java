package graudationProject.graduation_judge.domain.Grade.service;

import graudationProject.graduation_judge.domain.Grade.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService{

    //@Autowired
    //private GradeDao gradeDao;


    @Override
    public void inputGrade(Grade grade) {
    }

    @Override
    public String getGradeByLec(String cNum) {
        return null;
    }

    @Override
    public void deleteGradeByLec(String cNum) {
    }

    @Override
    public void deleteGradeByMember(String memberId) {

    }
}
