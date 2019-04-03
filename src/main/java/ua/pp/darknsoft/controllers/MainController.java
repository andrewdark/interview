package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.dao.interfaces.InterviewDao;
import ua.pp.darknsoft.domain.entity.Candidate;
import ua.pp.darknsoft.domain.entity.Interview;

@Controller
public class MainController {

    @Autowired
    InterviewDao id;

    @GetMapping("/")
    public String index(Model dasModel) {
        Interview inter = new Interview();
        Candidate candy = new Candidate();
        //candy.setFirstName("aaaaab");
        inter.setCandidate(candy);
        dasModel.addAttribute("interviews", id.getFilteredInterviews(inter));

        return "index";
    }
}
