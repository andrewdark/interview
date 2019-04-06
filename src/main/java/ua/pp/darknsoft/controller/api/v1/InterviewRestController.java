package ua.pp.darknsoft.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.domain.dto.FilterInterviewBuilder;
import ua.pp.darknsoft.domain.dto.FilterInterviewDto;
import ua.pp.darknsoft.service.interfaces.InterviewService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class InterviewRestController {
    @Autowired
    InterviewService interviewService;

    @PostMapping(value = "/filtered_interviews")
    public ResponseEntity<List<FilterInterviewBuilder>> getFilteringInterview(@RequestBody FilterInterviewDto filterInterviewDto){
        System.out.println(filterInterviewDto.getDate());
        FilterInterviewBuilder fib = new FilterInterviewBuilder.Builder()
                .withPosition(filterInterviewDto.getPosition())
                .withStatus(filterInterviewDto.getStatus())
                .withDate(filterInterviewDto.getDate())
                .withFirsName(filterInterviewDto.getFirstName())
                .withLastName(filterInterviewDto.getLastName())
                .withEmail(filterInterviewDto.getEmail()).build();
        List<FilterInterviewBuilder> fids = interviewService.findWithFilter(fib);
        return new ResponseEntity<List<FilterInterviewBuilder>>(fids,HttpStatus.OK);
    }
}
