package ua.pp.darknsoft.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import ua.pp.darknsoft.domain.dto.CandidateDto;
import ua.pp.darknsoft.domain.entity.Candidate;

public class CandidateDtoToCandidate implements Converter<CandidateDto, Candidate> {
    private final Object $lock = new Object[0];

    @Nullable
    @Override
    public Candidate convert(CandidateDto candidateDto) {
        synchronized ($lock) {
            if (candidateDto == null) {
                return null;
            }
            final Candidate candidate = new Candidate();
            candidate.setId(candidateDto.getId());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setFirstName(candidateDto.getFirstName());
            candidate.setLastName(candidateDto.getLastName());
            candidate.setPhone(candidateDto.getPhone());
            candidate.setSkype(candidateDto.getSkype());

            return candidate;
        }
    }

}
