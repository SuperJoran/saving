package be.ghostwritertje.dao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Ghostwritertje
 * Date: 29-Sep-16.
 */
@Configuration
public class PersistenceConfig {

    @Bean
    public DataSource dataSource(
            @Value("${OPENSHIFT_POSTGRESQL_DB_HOST}") String host,
            @Value("${OPENSHIFT_POSTGRESQL_DB_PORT}") String port,
            @Value("${OPENSHIFT_POSTGRESQL_DB_USERNAME}") String username,
            @Value("${OPENSHIFT_POSTGRESQL_DB_PASSWORD}") String password,
            @Value("${OPENSHIFT_POSTGRESQL_DB_URL}") String url
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://"+host + ":"+ port + "/saving" + "?user=" + username + "&password=" + password);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        System.out.println(url);
        return dataSource;
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        Properties properties = new Properties();
        properties.setProperty("connection.pool_size", "1");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        properties.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
        properties.setProperty("show_sql", "false");
        properties.setProperty("hbm2ddl.auto", "create");
        sessionFactory.setHibernateProperties(properties);

        return sessionFactory;
    }
}
