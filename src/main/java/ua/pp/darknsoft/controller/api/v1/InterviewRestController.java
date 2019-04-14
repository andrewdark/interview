package ua.pp.darknsoft.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.domain.dto.FilterInterviewBuilder;
import ua.pp.darknsoft.domain.dto.FilterInterviewDto;
import ua.pp.darknsoft.domain.dto.InterviewDto;
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
        return new ResponseEntity<InterviewDto>(interviewService.save(interviewDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/interviews/{id}")
    public ResponseEntity<InterviewDto> updateInterviewDto(@Validated @PathVariable Long id, @RequestBody InterviewDto interviewDto) {
        InterviewDto currentInterviewDto = interviewService.findById(id).get();
        if (currentInterviewDto == null) {
            return new ResponseEntity<InterviewDto>(HttpStatus.NOT_FOUND);
        }
        currentInterviewDto.setPosition(interviewDto.getPosition());
        currentInterviewDto.setStatus(interviewDto.getStatus());
        currentInterviewDto.setDate(interviewDto.getDate());
        if (interviewDto.getCandidateDto() != null) {
            currentInterviewDto.getCandidateDto().setFirstName(interviewDto.getCandidateDto().getFirstName());
            currentInterviewDto.getCandidateDto().setLastName(interviewDto.getCandidateDto().getLastName());
            currentInterviewDto.getCandidateDto().setEmail(interviewDto.getCandidateDto().getEmail());
            currentInterviewDto.getCandidateDto().setSkype(interviewDto.getCandidateDto().getSkype());
            currentInterviewDto.getCandidateDto().setPhone(interviewDto.getCandidateDto().getPhone());
        }
        //TODO
//        if (interviewDto.getInterviewerDtoSet() != null) {
//
//            currentInterviewDto.setInterviewerDtoSet(interviewDto.getInterviewerDtoSet());
//        }
//        if (interviewDto.getNoteDtoSet() != null) {
//            currentInterviewDto.setNoteDtoSet(interviewDto.getNoteDtoSet());
//        }
        return new ResponseEntity<InterviewDto>(interviewService.update(currentInterviewDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/interviews/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        interviewService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/filtered_interviews")
    public ResponseEntity<List<FilterInterviewBuilder>> getFilteringInterview(@RequestBody FilterInterviewDto filterInterviewDto) {
        System.out.println(filterInterviewDto.getDate());
        FilterInterviewBuilder fib = new FilterInterviewBuilder.Builder()
                .withPosition(filterInterviewDto.getPosition())
                .withStatus(filterInterviewDto.getStatus())
                .withDate(filterInterviewDto.getDate())
                .withFirsName(filterInterviewDto.getFirstName())
                .withLastName(filterInterviewDto.getLastName())
                .withEmail(filterInterviewDto.getEmail()).build();
        List<FilterInterviewBuilder> fids = interviewService.findWithFilter(fib);
        return new ResponseEntity<List<FilterInterviewBuilder>>(fids, HttpStatus.OK);
    }
}
