package ua.pp.darknsoft.dao.implementations;

import ua.pp.darknsoft.dao.interfaces.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *     This class represented abstract and generic implementations of
 *     {@link ua.pp.darknsoft.dao.interfaces.GenericDao} interface.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 *
 * @since 1.0
 */
public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {

    private final Class<T> clazz;
    private EntityManager entityManager;

    protected GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected Class<T> getClazz() {
        return clazz;
    }

    @Override
    public T findById(ID id) {
        return getEntityManager().find(getClazz(), id);
    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("from "+getClazz().getSimpleName(), getClazz()).getResultList();
    }

    @Override
    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        if(entities != null && !entities.isEmpty()){
            Iterator<T> iterator = entities.iterator();
            while(iterator.hasNext()){
                this.delete(iterator.next());
            }
        }
    }
}
