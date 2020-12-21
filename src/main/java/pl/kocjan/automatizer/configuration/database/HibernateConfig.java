package pl.kocjan.automatizer.configuration.database;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig{

@Bean
public DataSource getDatasource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("user");
        dataSource.setPassword("user");

        return dataSource;
    }

@Bean
public SessionFactory getSessionFactory() throws IOException{

LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
 sessionFactoryBean.setPackagesToScan("pl");
  sessionFactoryBean.setHibernateProperties(getHibernateProperties());
    sessionFactoryBean.setDataSource(getDatasource());
    sessionFactoryBean.afterPropertiesSet();

    return sessionFactoryBean.getObject();
}

private static Properties getHibernateProperties() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");

        return hibernateProperties;
    }
}