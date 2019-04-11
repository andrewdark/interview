package ua.pp.darknsoft.service.implementation;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ua.pp.darknsoft.dao.interfaces.*;
import ua.pp.darknsoft.domain.converter.*;
import ua.pp.darknsoft.domain.dto.*;
import ua.pp.darknsoft.domain.entity.*;
import ua.pp.darknsoft.service.interfaces.*;

import java.util.*;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewDao interviewDao;

    @Autowired
    InterviewToInterviewDto toInterviewDto;
    @Autowired
    InterviewDtoToInterview toInterview;

    @Override
    public Optional<InterviewDto> findById(Long id) {
        Optional<InterviewDto> interviewOptional = Optional.ofNullable(toInterviewDto.convert(interviewDao.findById(id)));
        return interviewOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InterviewDto> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
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
        return interviewDao.isExist(toInterview.convert(interviewDto));
    }

    @Override
    public void deleteById(Long id) {

    }

    //TODO
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
