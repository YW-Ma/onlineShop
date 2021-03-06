package com.study.onlineShop;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import com.study.onlineShop.Confidentials.*;
// let spring help to initiate spring mvc related beans
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class ApplicationConfig {

  @Bean(name = "sessionFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("com.study.onlineShop.entity");
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  @Bean(name = "dataSource")
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//    dataSource.setUrl("jdbc:mysql://" + Confidentials.RDS_ENDPOINT + ":3306/ecommerce?createDatabaseIfNotExist=true&serverTimezone=UTC");
//    dataSource.setUsername(Confidentials.USERNAME);
//    dataSource.setPassword(Confidentials.PASSWORD);
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

    final String INSTANCE = "localhost";
    final String PORT_NUM = "3306";
    final String DB_NAME = "online_shop_db";
    final String USERNAME = "root";
    final String PASSWORD = "asdf";
    dataSource.setUrl("jdbc:mysql://" + INSTANCE + ":" + PORT_NUM + "/" + DB_NAME);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);

    return dataSource;
  }

  private final Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
    hibernateProperties.setProperty("hibernate.show_sql", "true");
    return hibernateProperties;
  }
}
