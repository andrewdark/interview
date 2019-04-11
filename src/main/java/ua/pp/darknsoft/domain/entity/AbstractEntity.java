package ua.pp.darknsoft.domain.entity;

import javax.persistence.*;

/**
 * <p>
 *     This class represented abstract Entity with one column <b>id</b> with is mandatory for all entities in the system
 *     and this column is a Primary key.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 *
 * @since 1.0
 */
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
