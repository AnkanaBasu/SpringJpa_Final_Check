package com.cognizant.moviecruiser.configuration;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.util.DateUtil;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.cognizant.moviecruiser")
@PropertySource(value = { "classpath:connection.properties", "classpath:hibernate.properties" })
@EnableJpaRepositories(basePackages = "com.cognizant.moviecruiser.repository")
public class AppConfig {

	@Autowired
	private Environment env;
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("driver"));
		ds.setUrl(env.getProperty("connection-url"));
		ds.setUsername(env.getProperty("user"));
		ds.setPassword(env.getProperty("password"));
		return ds;
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(new String[] { "com.cognizant.moviecruiser.model" });

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(additionalProperties());

		return entityManagerFactoryBean;
	}
	final Properties additionalProperties() {
		// TODO Auto-generated method stub
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache",
				env.getProperty("hibernate.cache.use_second_level_cache"));
		hibernateProperties.setProperty("hibernate.cache.use_query_cache",
				env.getProperty("hibernate.cache.use_query_cache"));
		hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans",
				env.getProperty("hibernate.enable_lazy_load_no_trans"));
		return hibernateProperties;
	}
	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	@Bean
	public Movie getAvatar() {
		return new Movie(1, "Avatar", "$2,787,965,087", true, DateUtil.convertToDate("15/03/2017"), "Science Fiction",
				true);
	}

	@Bean
	public Movie getAvengers() {
		return new Movie(2, "The Avengers", "$1,518,812,988", true, DateUtil.convertToDate("23/12/2017"), "Superhero",
				false);
	}

	@Bean
	public Movie getTitanic() {
		return new Movie(3, "Titanic", "$2,187,463,944", true, DateUtil.convertToDate("21/08/2017"), "Romance", false);
	}

	@Bean
	public Movie getJurassic_world() {
		return new Movie(4, "Jurassic World", "$1,671,713,208", false, DateUtil.convertToDate("02/07/2017"),
				"Science Fiction", true);
	}

	@Bean
	public Movie getEnd_game() {
		return new Movie(5, "Avengers: End Game", "$2,750,760,348", true, DateUtil.convertToDate("02/11/2022"),
				"Superhero", true);
	}

	@Bean
	public List<Movie> getMovieList() {
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(getAvatar());
		movies.add(getAvengers());
		movies.add(getTitanic());
		movies.add(getJurassic_world());
		movies.add(getEnd_game());
		return movies;
	}
}
