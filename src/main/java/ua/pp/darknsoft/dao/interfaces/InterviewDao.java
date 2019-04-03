package ua.pp.darknsoft.dao.interfaces;

import ua.pp.darknsoft.domain.entity.Interview;

import java.util.List;

public interface InterviewDao extends GenericDao<Interview, Long> {
    public List<Interview> getFilteredInterviews(Interview interview);
}
