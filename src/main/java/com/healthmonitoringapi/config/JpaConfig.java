package com.healthmonitoringapi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = App.class)
@PropertySource("classpath:application.properties")
class JpaConfig {
	
	@Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create()
//        .driverClass(environment.getRequiredProperty("spring.datasource.driverClassName"))
        .url(environment.getRequiredProperty("spring.datasource.url"))
        .username(environment.getRequiredProperty("spring.datasource.username"))
        .password(environment.getRequiredProperty("spring.datasource.password"))
        .build();
        return dataSource;
    }

//	@Bean
//	public EntityManagerFactory entityManagerFactory() {
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(false);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//
//		String entities = "com.healthmonitoringapi";
//		factory.setPackagesToScan(entities);
//		factory.setDataSource(dataSource());
//		factory.afterPropertiesSet();
//
//		return factory.getObject();
//	}

//	@Bean
//	@Qualifier(value = "jpaTransactionManager")
//	public PlatformTransactionManager transactionManager() {
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(entityManagerFactory());
//		return txManager;
//	}
}