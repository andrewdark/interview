package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.pp.darknsoft.domain.dto.FilterInterviewDto;
import ua.pp.darknsoft.domain.entity.Candidate;
import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.service.interfaces.InterviewService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    InterviewService interviewService;

    @RequestMapping(value = "/")
    public String index(Model dasModel) {
        Interview interview = new Interview();
        List<FilterInterviewDto> interviews = interviewService.findWithFilter(interview);
        dasModel.addAttribute("interviews", interviews);
        return "index";
    }
}
