package com.tradehelper.core.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
@ComponentScan(basePackages = "com.tradehelper.core")
@EntityScan(basePackages = "com.tradehelper.core")
@PropertySource(value = "classpath:application.properties")
@EnableMongoRepositories(basePackages = "com.tradehelper.core")
public class TradeHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeHelperApplication.class, args);
	}
}
