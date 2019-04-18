package ua.pp.darknsoft.service.interfaces;

import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.dto.InterviewFilterDto;
import ua.pp.darknsoft.domain.dto.InterviewerDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InterviewService {

    InterviewDto create(InterviewDto interview);
    void addInterviewer(InterviewerDto interviewer);
    Optional<InterviewDto> findById(Long id);
    InterviewDto update(InterviewDto interviewDto);
    List<InterviewDto> findWithFilter(InterviewFilterDto filter);
    Map<InterviewerDto, Boolean> hasNote(InterviewDto interviewDto);
    Boolean isExist(InterviewDto interviewDto);

    List<InterviewDto> findAll();

    void deleteById(Long id);
}
