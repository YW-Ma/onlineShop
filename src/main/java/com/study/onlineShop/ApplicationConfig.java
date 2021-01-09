package com.study.onlineShop;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import com.study.onlineShop.Confidentials.*;

@Configuration
public class ApplicationConfig {
  // reference: https://blog.csdn.net/J080624/article/details/82937616

  @Bean(name = "sessionFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource()); //告诉它链接的数据库(下方函数）
    sessionFactory.setPackagesToScan("com.study.onlineShop.entity");  //告诉它要扫描的路径
    sessionFactory.setHibernateProperties(hibernateProperties()); //告诉它我的配置（下方函数）
    return sessionFactory;
  }

  @Bean(name = "dataSource")
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    // 只需要修改红色部分, 保留其他内容
    dataSource.setUrl("jdbc:mysql://" + Confidentials.RDS_ENDPOINT + ":3306/ecommerce?createDatabaseIfNotExist=true&serverTimezone=UTC");
    dataSource.setUsername(Confidentials.USERNAME);
    dataSource.setPassword(Confidentials.PASSWORD);

    return dataSource;
  }


  private final Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"); // if mysql version is 8, MySQL5InnoDBDialect
    hibernateProperties.setProperty("hibernate.show_sql", "true");
    return hibernateProperties;
  }
}
