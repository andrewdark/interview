package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.domain.dto.*;
import ua.pp.darknsoft.service.interfaces.*;

import java.util.*;

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

    @RequestMapping(value = "/interviews/edit/{id}")
    public String showIntervievEdit(@PathVariable Long id, Model dasModel) {

        InterviewDto interviewDto = interviewService.findById(id).orElse(new InterviewDto());

        dasModel.addAttribute("interview", interviewDto);
        dasModel.addAttribute("hasNote", interviewService.hasNote(interviewDto));
        return "interview_edit";
    }
}
