package org.manu.springjpa;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("org.manu.springjpa")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement()
public class JpaConfiguration {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_DELEGATOR = "hibernate.ejb.naming_strategy_delegator";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_DBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
//    private static final String PROPERTY_NAME_HIBERNATE_IMPORT_FILES = "hibernate.hbm2ddl.import_files";

    @Resource
    private Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        cpds.setDriverClass(env.getProperty(PROPERTY_NAME_DATABASE_DRIVER));
        cpds.setJdbcUrl(env.getProperty(PROPERTY_NAME_DATABASE_URL));
        cpds.setUser(env.getProperty(PROPERTY_NAME_DATABASE_USERNAME));
        cpds.setPassword(env.getProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);

        return cpds;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws Exception {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceXmlLocation("META-INF/persistence.xml");
        entityManagerFactoryBean.setPersistenceUnitName("application");
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_DELEGATOR, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_DELEGATOR));
        properties.put(PROPERTY_NAME_HIBERNATE_DBM2DDL_AUTO, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DBM2DDL_AUTO));
//        properties.put(PROPERTY_NAME_HIBERNATE_IMPORT_FILES, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_IMPORT_FILES));
        return properties;
    }
}
