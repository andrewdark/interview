package ua.pp.darknsoft.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.dto.FilterInterviewBuilder;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.entity.Interview;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InterviewService {

    Optional<InterviewDto> findById(Long id);

    List<InterviewDto> findAll();

    List<FilterInterviewBuilder> findWithFilter(FilterInterviewBuilder filterInterviewBuilder);

    Page<InterviewDto> findAll(Pageable pageable);

    InterviewDto save(InterviewDto interview);

    InterviewDto update(InterviewDto interview);

    boolean isExist(InterviewDto interview);

    void deleteById(Long id);

    Map<InterviewerDto, Boolean> hasNote(InterviewDto interviewDto);
}
