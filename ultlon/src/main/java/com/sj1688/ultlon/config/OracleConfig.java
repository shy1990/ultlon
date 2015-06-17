package com.sj1688.ultlon.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.sj1688.ultlon.dao.oracle",sqlSessionFactoryRef="oracleFactory")
public class OracleConfig {
	
	/**
	 * Oracle数据库
	 * B2B项目数据库
	 * @return
	 */
	@Bean(name = "oracleDataSource")
	@ConfigurationProperties(prefix="datasource.oracle")
	public DataSource oracleDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="oracleFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(oracleDataSource());
		return sessionFactory.getObject();
	}
	
}
