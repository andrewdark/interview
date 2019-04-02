package ua.pp.darknsoft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.pp.darknsoft.dao.configuration.PersistenceJPAConfig;
import ua.pp.darknsoft.dao.interfaces.CandidateDao;
import ua.pp.darknsoft.domain.entity.Candidate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Rollback
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class},
        loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class CandidateDaoITest {

    @Autowired
    private CandidateDao cd;

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
