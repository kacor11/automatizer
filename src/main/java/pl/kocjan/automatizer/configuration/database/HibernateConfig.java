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
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("localhost:3306");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

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

@Bean
    public HibernateTransactionManager getTransactionManager() throws IOException{
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory());

        return transactionManager;
   }


private static Properties getHibernateProperties() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.put("hibernate.show_sql", true);

        return hibernateProperties;
    }
}