package ua.pp.darknsoft.dao.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>
 *     This class is represented persistence JPA configuration.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 *
 * @since 1.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ua.pp.darknsoft.dao")
@PropertySource("classpath:/jpa_h2.properties")
@Profile("test")
public class PersistenceJPAConfig {

    private final Environment env;

    @Autowired
    public PersistenceJPAConfig(Environment env) {
        this.env = env;
    }

    /**
     * Creates Bean of JPA {@link EntityManagerFactory} for further injection to JPA-based DAOs via
     * dependency injection.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("ua.pp.darknsoft.domain");
        entityManagerFactory.setDataSource(dataSource());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(additionalProperties());
        return entityManagerFactory;
    }

    /**
     * Creates Bean of {@link DataSource} which provides connections to the physical data source
     * that this DataSource object represents.
     * Configuration settings are read from a {@code jpa_h2.properties} config file.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver"));
        dataSource.setUrl(env.getProperty("hibernate.connection.url"));
        dataSource.setUsername(env.getProperty("hibernate.connection.username"));
        dataSource.setPassword(env.getProperty("hibernate.connection.password"));
        return dataSource;
    }

    /**
     * Creates a transaction manager bean of {@link JpaTransactionManager}
     * which is implementations of {@link PlatformTransactionManager} that integrates
     * the JPA provider with the Spring transaction mechanism.
     *
     * @param entityManagerFactory entity manager factory
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * Creates Bean post-processor that automatically applies persistence exception translation to any
     * bean marked with Spring's @{@link org.springframework.stereotype.Repository Repository}
     * annotation
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Addition properties for DataSource Bean
     *
     * @see #entityManagerFactory
     * @see #dataSource
     * such as
     * hibernate.dialect
     * hibernate.show_sql
     * hibernate.format_sql
     * and so on must be provided here
     */
    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
