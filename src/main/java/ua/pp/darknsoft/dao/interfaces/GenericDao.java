package ua.pp.darknsoft.dao.interfaces;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *     This class represented generic interface for all DAO.
 * </p>
 *
 * @param <T> an Entity for which DAO implementations is created.
 * @param <ID> an Unique Identifier of the Entity
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 *
 * @since 1.0
 */
public interface GenericDao<T, ID> {

    T findById(ID id);
    List<T> findAll();
    void save(T entity);
    T update(T entity);
    void delete(T entity);
    void deleteAll(Collection<T> entities);

}
