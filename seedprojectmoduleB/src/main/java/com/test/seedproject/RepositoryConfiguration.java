package com.test.seedproject;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
// scans and enable repositories
@EnableJpaRepositories(basePackages = {"com.test.seedproject.domain.dataaccessobjects"})
@EnableTransactionManagement()
// scans and enable entities
@EntityScan(basePackages = {"com.test.seedproject.domain"})
public class RepositoryConfiguration {

  private static Logger logger = Logger.getLogger(RepositoryConfiguration.class.getName());


  @Bean
  public CurrentDateTimeService getCurrentDateTimeService() {
    return new CurrentDateTimeService();
  }


  @Primary
  @Bean(destroyMethod = "close")
  public DataSource dataSource(Environment env) {
    HikariConfig dataSourceConfig = new HikariConfig();
    dataSourceConfig.setDriverClassName(env.getRequiredProperty("spring.datasource.driverClassName"));
    dataSourceConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
    dataSourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
    dataSourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
    dataSourceConfig.setPoolName("testapp");

    dataSourceConfig.setConnectionTestQuery(env.getRequiredProperty("spring.datasource.validationQuery"));

    dataSourceConfig
        .setConnectionTimeout(Long.parseLong(env.getRequiredProperty("spring.datasource.connectionTimeout")));
    dataSourceConfig.setIdleTimeout(Long.parseLong(env.getRequiredProperty("spring.datasource.idleTimeout")));
    dataSourceConfig.setLeakDetectionThreshold(
        Long.parseLong(env.getRequiredProperty("spring.datasource.leakDetection.threshold")));
    dataSourceConfig.setMaximumPoolSize(Integer.parseInt(env.getRequiredProperty("spring.datasource.maximumPoolSize")));
    dataSourceConfig.setMaxLifetime(Integer.parseInt(env.getRequiredProperty("spring.datasource.maxLifetime")));
    dataSourceConfig.setMinimumIdle(Integer.parseInt(env.getRequiredProperty("spring.datasource.minimumIdle")));


    dataSourceConfig.addDataSourceProperty("prepStmtCacheSize",
        Integer.parseInt(env.getRequiredProperty("spring.dataSource.prepStmtCacheSize")));
    dataSourceConfig.addDataSourceProperty("prepStmtCacheSqlLimit",
        Integer.parseInt(env.getRequiredProperty("spring.dataSource.prepStmtCacheSqlLimit")));
    dataSourceConfig.addDataSourceProperty("useServerPrepStmts",
        Boolean.parseBoolean(env.getRequiredProperty("spring.dataSource.cachePrepStmts")));

    dataSourceConfig.addDataSourceProperty("useLocalSessionState", true);
    dataSourceConfig.addDataSourceProperty("useLocalTransactionState", true);
    dataSourceConfig.addDataSourceProperty("rewriteBatchedStatements", true);
    dataSourceConfig.addDataSourceProperty("cacheResultSetMetadata", true);
    dataSourceConfig.addDataSourceProperty("cacheServerConfiguration", true);
    dataSourceConfig.addDataSourceProperty("elideSetAutoCommits", true);
    dataSourceConfig.addDataSourceProperty("maintainTimeStats", false);

    return new HikariDataSource(dataSourceConfig);

  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource);
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan("com.test.seedproject.domain",
        "org.springframework.data.jpa.convert.threeten");
    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
    jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
    jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("spring.jpa.hibernate.naming-strategy"));
    jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("spring.jpa.show-sql"));
    jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("spring.jpa.format_sql"));
    entityManagerFactoryBean.setJpaProperties(jpaProperties);
    return entityManagerFactoryBean;
  }

  @Bean(name = "transactionManager")
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }

}
