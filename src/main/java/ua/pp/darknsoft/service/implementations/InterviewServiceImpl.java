package ua.pp.darknsoft.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.pp.darknsoft.dao.interfaces.InterviewDao;
import ua.pp.darknsoft.domain.converter.InterviewDtoToInterview;
import ua.pp.darknsoft.domain.converter.InterviewToInterviewDto;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.dto.InterviewFilterDto;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.service.interfaces.InterviewService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

/**
 * <p>
 * This class is implementations of {@link InterviewService}
 * </p>
 *
 * @author <a href='mailto:antoxalanio@gmail.com'>Anton Lomakin</a>
 * @author <a href='mailto:samsonov.a@ukr.net'>Andrew Samsonov</a>
 * @since 1.0
 */
@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

    private InterviewDao interviewDao;
    private InterviewDtoToInterview interviewDtoToInterview;
    private InterviewToInterviewDto interviewToInterviewDto;
    private Set<InterviewerDto> interviewers = new HashSet<>();

    @Override
    public InterviewDto create(InterviewDto interviewDto) {
        checkInterviewDtoAsNull(interviewDto);
        interviewDto.setInterviewerDtoSet(interviewers);

        Interview interview = interviewDtoToInterview.convert(interviewDto);
        interviewDao.save(interview);
        return interviewToInterviewDto.convert(interview);

    }

    @Override
    public void addInterviewer(InterviewerDto interviewerDto) {
        notNull(interviewerDto, "Interviewer cannot be null");
        notNull(interviewerDto.getEmail(), "Email cannot be null");
        notNull(interviewerDto.getFirstName(), "First name cannot be null");
        notNull(interviewerDto.getLastName(), "Last name cannot be null");

        interviewers.add(interviewerDto);
    }

    @Override
    public Optional<InterviewDto> findById(Long id) {
        notNull(id, "Id cannot be null");
        return Optional.ofNullable(interviewToInterviewDto.convert(interviewDao.findById(id)));
    }

    @Autowired
    public void setInterviewDao(InterviewDao interviewDao) {
        this.interviewDao = interviewDao;
    }

    @Autowired
    public void setInterviewDtoToInterview(InterviewDtoToInterview interviewDtoToInterview) {
        this.interviewDtoToInterview = interviewDtoToInterview;
    }

    @Autowired
    public void setInterviewToInterviewDto(InterviewToInterviewDto interviewToInterviewDto) {
        this.interviewToInterviewDto = interviewToInterviewDto;
    }

    @Override
    public InterviewDto update(InterviewDto interviewDto) {
        checkInterviewDtoAsNull(interviewDto);

        Interview currentInterview = interviewDao.findById(interviewDto.getId());

        if (currentInterview == null) {
            // return smth
        }
        currentInterview.setPosition(interviewDto.getPosition());
        currentInterview.setStatus(interviewDto.getStatus());
        currentInterview.setDate(interviewDto.getDate());
        currentInterview.getCandidate().setFirstName(interviewDto.getCandidateDto().getFirstName());
        currentInterview.getCandidate().setLastName(interviewDto.getCandidateDto().getLastName());
        currentInterview.getCandidate().setEmail(interviewDto.getCandidateDto().getEmail());
        currentInterview.getCandidate().setSkype(interviewDto.getCandidateDto().getSkype());
        currentInterview.getCandidate().setPhone(interviewDto.getCandidateDto().getPhone());

        return interviewToInterviewDto.convert(interviewDao.update(currentInterview));

    }

    @Override
    public List<InterviewDto> findWithFilter(InterviewFilterDto filter) {
        Assert.notNull(filter, "BAD param. InterviewFilterDto should not be null");

        return interviewDao.getInterviewWithFilter(filter)
                .stream().map(interview -> interviewToInterviewDto.convert(interview))
                .collect(Collectors.toList());
    }

    @Override
    public Map<InterviewerDto, Boolean> hasNote(InterviewDto interviewDto) {
        Assert.notNull(interviewDto, "BAD param. Interview should not be null");

        Map<InterviewerDto, Boolean> interviewerBooleanMap = new HashMap<>();

        interviewDto.getInterviewerDtoSet().stream().forEach(master -> interviewerBooleanMap.put(master, false));
        interviewDto.getNoteDtoSet().stream().map(n -> n.getInterviewerDto()).forEach(master -> interviewerBooleanMap.put(master, true));

        return interviewerBooleanMap;
    }

    //TODO
    @Override
    public Boolean isExist(InterviewDto interviewDto) {
        return null;
    }

    @Override
    public List<InterviewDto> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private void checkInterviewDtoAsNull(InterviewDto interviewDto) {
        notNull(interviewDto, "Interview cannot be null");
        notNull(interviewDto.getCandidateDto(), "Candidate cannot be null");
        notNull(interviewDto.getCandidateDto().getEmail(), "Email cannot be null");
        notNull(interviewDto.getCandidateDto().getFirstName(), "First name cannot be null");
        notNull(interviewDto.getCandidateDto().getLastName(), "Last name cannot be null");
        notNull(interviewDto.getDate(), "Date cannot be null");
        notNull(interviewDto.getPosition(), "Position cannot be null");
        notNull(interviewDto.getStatus(), "Status cannot be null");
    }
}
