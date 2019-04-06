package ua.pp.darknsoft.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.pp.darknsoft.domain.dto.FilterInterviewBuilder;
import ua.pp.darknsoft.domain.entity.Interview;

import java.util.List;
import java.util.Optional;

public interface InterviewService {

    Optional<Interview> findById(Long id);

    List<Interview> findAll();

    List<FilterInterviewBuilder> findWithFilter(FilterInterviewBuilder filterInterviewBuilder);

    Page<Interview> findAll(Pageable pageable);

    Interview save(Interview interview);

    boolean isExist(Interview interview);

    void deleteById(Long id);
}
