package ua.pp.darknsoft.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.dao.configuration.PersistenceJPAConfig;
import ua.pp.darknsoft.dao.interfaces.CandidateDao;
import ua.pp.darknsoft.domain.entity.Candidate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Rollback
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class})
@ActiveProfiles("test")
public class CandidateDaoITest {

    @Autowired
    private CandidateDao cd;

    @Autowired
    public CandidateDaoITest(CandidateDao cd) {
        this.cd = cd;
    }

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Test
    public void test() {
        Candidate can = new Candidate();
        can.setFirstName("Ololo");
        can.setLastName("Evich");
        can.setEmail("h2mail@ukr.net");
        cd.save(can);
        entityManager.flush();

        Long id = can.getId();
        Assertions.assertNotNull(id);
    }
}
