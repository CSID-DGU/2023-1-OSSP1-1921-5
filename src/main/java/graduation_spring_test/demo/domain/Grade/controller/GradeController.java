package graduation_spring_test.demo.domain.Grade.controller;

import graduation_spring_test.demo.domain.Grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;
}
