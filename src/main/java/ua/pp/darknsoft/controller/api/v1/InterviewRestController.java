package ua.pp.darknsoft.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.domain.builder.InterviewFilterBuilder;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.dto.InterviewFilterDto;
import ua.pp.darknsoft.service.interfaces.InterviewService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class InterviewRestController {
    @Autowired
    InterviewService interviewService;

    @GetMapping(value = "/interviews/{id}")
    public ResponseEntity<InterviewDto> getSingleInterviewDto(@PathVariable Long id) {
        Optional<InterviewDto> interviewDto = interviewService.findById(id);

        if (interviewDto.isPresent()) {
            return new ResponseEntity<InterviewDto>(interviewDto.get(), HttpStatus.OK);
        }
        return new ResponseEntity<InterviewDto>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/interviews")
    public ResponseEntity<List<InterviewDto>> getAllInterviewDto() {

        return new ResponseEntity<List<InterviewDto>>(interviewService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/interviews")
    public ResponseEntity<InterviewDto> createInterviewDto(@Validated @RequestBody InterviewDto interviewDto) {
        if (interviewService.isExist(interviewDto)) {
            return new ResponseEntity<InterviewDto>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<InterviewDto>(interviewService.create(interviewDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/interviews/{id}")
    public ResponseEntity<InterviewDto> updateInterviewDto(@Validated @PathVariable Long id, @RequestBody InterviewDto interviewDto) {
        Optional<InterviewDto> optionalInterviewDto = interviewService.update(interviewDto);
        if (optionalInterviewDto.isPresent()) {
            return new ResponseEntity<InterviewDto>(optionalInterviewDto.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/interviews/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        interviewService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/filtered_interviews")
    public ResponseEntity<List<InterviewDto>> getFilteringInterview(@RequestBody InterviewFilterDto filterInterviewDto) {

        InterviewFilterDto fib = new InterviewFilterBuilder()
                .withPosition(filterInterviewDto.getPosition())
                .withStatus(filterInterviewDto.getStatus())
                .withDate(filterInterviewDto.getDate())
                .withFirsName(filterInterviewDto.getFirstName())
                .withLastName(filterInterviewDto.getLastName())
                .withEmail(filterInterviewDto.getEmail()).build();
        List<InterviewDto> fids = interviewService.findWithFilter(fib);
        return new ResponseEntity<List<InterviewDto>>(fids, HttpStatus.OK);
    }
}
