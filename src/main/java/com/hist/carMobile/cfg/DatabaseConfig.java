package com.hist.carMobile.cfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.cfg<br/>
 * <B>File Name : </B>DatabaseConfig<br/>
 * <B>Description</B>
 * <ul>
 * <li>Database 를 mybatis에서 사용하기 위한 설정
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
@Configuration
@MapperScan(basePackages = "com.hist.carMobile.mapper")
public class DatabaseConfig {

	private final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

	@Autowired
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		logger.debug(">>>>> >>>>> >>>>> DatabaseConfig - SqlSessionFactory");
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}

	@Primary
	@Bean(name = "transactionManager")
	PlatformTransactionManager transactionManager() {
		logger.debug(">>>>> >>>>> >>>>> DatabaseConfig - PlatformTransactionManager");
		return new DataSourceTransactionManager(dataSource);
	}

}