package tw.kgips.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {
    private final Environment env;
    private final EntityManagerFactoryBuilder builder;

    @Autowired
    public PersistenceJPAConfig(Environment env, @Lazy EntityManagerFactoryBuilder builder) {
        this.env = env;
        this.builder = builder;
    }

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return builder
            .dataSource(dataSource())
            .packages("tw.kgips.persistence.entity")
            .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Primary
    @Bean(name = "sessionFactory")
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("tw.kgips.persistence.entity");
        sessionFactory.setHibernateProperties(getProperties());

        return sessionFactory;
    }

    private Properties getProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", this.env.getProperty("spring.jpa.properties.hibernate.dialect"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", this.env.getProperty("spring.jpa.hibernate.ddl-auto"));
        return hibernateProperties;
    }
}
