package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.pp.darknsoft.domain.dto.FilterInterviewBuilder;
import ua.pp.darknsoft.service.interfaces.InterviewService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    InterviewService interviewService;

    @RequestMapping(value = "/")
    public String index(Model dasModel) {
        List<FilterInterviewBuilder> interviews = interviewService.findWithFilter(new FilterInterviewBuilder.Builder().build());
        dasModel.addAttribute("interviews", interviews);
        return "index";
    }
}
