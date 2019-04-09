package ua.pp.darknsoft.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.dao.interfaces.InterviewDao;
import ua.pp.darknsoft.domain.converter.InterviewDtoToInterview;
import ua.pp.darknsoft.domain.converter.InterviewToInterviewDto;
import ua.pp.darknsoft.domain.dto.FilterInterviewBuilder;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.service.interfaces.InterviewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewDao interviewDao;

    @Autowired
    InterviewToInterviewDto toInterviewDto;

    InterviewDtoToInterview toInterview;

    @Override
    public Optional<InterviewDto> findById(Long id) {
        Optional<InterviewDto> userOptional = Optional.of(new InterviewDto());
        return userOptional;
    }

    @Override
    public List<InterviewDto> findAll() {
        return null;
    }

    @Override
    public Page<InterviewDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public InterviewDto save(InterviewDto interviewDto) {

        interviewDao.save(toInterview.convert(interviewDto));

        return interviewDto;
    }

    @Override
    public InterviewDto update(InterviewDto interviewDto) {

        return toInterviewDto.convert(interviewDao.update(toInterview.convert(interviewDto)));
    }

    @Override
    public boolean isExist(InterviewDto interviewDto) {
        return false;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<FilterInterviewBuilder> findWithFilter(FilterInterviewBuilder filterInterviewBuilder) {

        List<FilterInterviewBuilder> filterInterviews = new ArrayList<>();
        for (Interview inter : interviewDao.getFilteredInterviews(filterInterviewBuilder)) {
            FilterInterviewBuilder fid = new FilterInterviewBuilder.Builder()
                    .withId(inter.getId())
                    .withPosition(inter.getPosition())
                    .withStatus(inter.getStatus())
                    .withDate(inter.getDate())
                    .withFirsName(inter.getCandidate().getFirstName())
                    .withLastName(inter.getCandidate().getLastName())
                    .withEmail(inter.getCandidate().getEmail())
                    .build();
            filterInterviews.add(fid);

        }
        return filterInterviews;
    }


}
