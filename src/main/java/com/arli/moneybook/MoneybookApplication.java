package com.arli.moneybook;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableTransactionManagement
public class MoneybookApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(MoneybookApplication.class, args);
	}

	@Bean
	public Mapper beanMapper(){
		return  new DozerBeanMapper();
	}
}
