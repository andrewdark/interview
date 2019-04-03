package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.dao.interfaces.InterviewDao;
import ua.pp.darknsoft.domain.entity.Candidate;
import ua.pp.darknsoft.domain.entity.Interview;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    InterviewDao interviewDao;

    @GetMapping("/")
    public String index(Model dasModel) {

        Interview inter = new Interview();
        Candidate candy = new Candidate();
        inter.setCandidate(candy);
        inter.setPosition("Java junior");
        List<Interview> interviewList = interviewDao.getFilteredInterviews(inter);
        System.out.println("INFO: " + interviewList.size());
        dasModel.addAttribute("total", interviewList.size());
        dasModel.addAttribute("interviews", interviewList);

        return "index";
    }

}
