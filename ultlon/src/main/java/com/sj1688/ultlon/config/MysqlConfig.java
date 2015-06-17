package com.sj1688.ultlon.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory", transactionManagerRef = "mysqlTransactionManager", basePackages = { "com.sj1688.ultlon.dao.mysql" })
public class MysqlConfig {
	@Bean(name = "mysqlDataSource")
	@Primary
	@ConfigurationProperties(prefix="datasource.mysql")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "mysqlEntityManagerFactory")
	public EntityManagerFactory  entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);
	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setDataSource(mysqlDataSource());
	    factory.setPackagesToScan("com.sj1688.ultlon.domain");
	    Properties jpaProperties = new Properties();
	    jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		factory.setJpaProperties(jpaProperties);
	    factory.afterPropertiesSet();
	    return factory.getObject();
		
	}

	@Bean(name = "mysqlTransactionManager")
	public PlatformTransactionManager transactionManagerSecondary() {
		JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory());
	    return txManager;
	}
	
}
