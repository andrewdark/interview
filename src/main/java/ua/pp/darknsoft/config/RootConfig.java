package ua.pp.darknsoft.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class RootConfig {

    @Autowired
    private Environment env;

    @Bean//(destroyMethod = "close")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("datasource.driver-class-name"));
        hikariConfig.setJdbcUrl(env.getProperty("datasource.url"));
        hikariConfig.setUsername(env.getProperty("datasource.username"));
        hikariConfig.setPassword(env.getProperty("datasource.password"));

        hikariConfig.setMaximumPoolSize(Integer.parseInt(env.getProperty("hikari.maximum-pool-size")));
        hikariConfig.setConnectionTestQuery(env.getProperty("hikari.connection-test-query"));
        hikariConfig.setPoolName(env.getProperty("hikari.pool-name"));

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", env.getProperty("hikari.cache-prep-stmts"));
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", env.getProperty("hikari.prep-stmt-cache-size"));
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", env.getProperty("hikari.prep-stmt-cache-sql-limit"));
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", env.getProperty("hikari.use-server-prep-stmts"));

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties properties = new Properties();
        properties.put("dialect", env.getProperty("jpa.dialect"));
        properties.put("hibernate.max_fetch_depth", Integer.parseInt(env.getProperty("jpa.max_fetch_depth")));
        properties.put("hibernate.jdbc.fetch_size", Integer.parseInt(env.getProperty("jpa.jdbc-fetch_size")));
        properties.put("hibernate.jdbc.batch_size", Integer.parseInt(env.getProperty("jpa.jdbc-batch_size")));
        properties.put("hibernate.show_sql", env.getProperty("jpa.show-sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("jpa.hbm2ddl-auto"));

        emf.setJpaProperties(properties);
        emf.setPackagesToScan("ua.pp.darknsoft");
        return emf;
    }

    @Bean
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf().getObject());
        return transactionManager;
    }
}
