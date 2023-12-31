package com.example.voting.services;

import org.hibernate.SessionFactory;
import com.example.voting.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource("classpath:application.properties")
public class HibernateConfig {

//    @Value("${jdbc.url}")
//    private String jdbcUrl;
//
//    @Value("${jdbc.username}")
//    private String jdbcUsername;
//
//    @Value("${jdbc.password}")
//    private String jdbcPassword;
//
//    @Value("${hibernate.dialect}")
//    private String hibernateDialect;
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(jdbcUrl);
//        dataSource.setUsername(jdbcUsername);
//        dataSource.setPassword(jdbcPassword);
//        return dataSource;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setAnnotatedClasses(
//                User.class
//        );
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", hibernateDialect);
//        return properties;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory);
//        return transactionManager;
//    }
}

