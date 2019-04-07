package ua.pp.darknsoft.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.domain.dto.CandidateDto;
import ua.pp.darknsoft.domain.entity.Candidate;

@Component
public class CandidateToCandidateDto implements Converter<Candidate, CandidateDto> {
    private final Object $lock = new Object[0];

    @Nullable
    @Override
    public CandidateDto convert(Candidate candidate) {
        synchronized ($lock) {
            if (candidate == null) {
                return null;
            }
            final CandidateDto candidateDto = new CandidateDto();
            candidateDto.setId(candidate.getId());
            candidateDto.setEmail(candidate.getEmail());
            candidateDto.setFirstName(candidate.getFirstName());
            candidateDto.setLastName(candidate.getLastName());
            candidateDto.setPhone(candidate.getPhone());
            candidateDto.setSkype(candidate.getSkype());
            return candidateDto;
        }
    }
}
