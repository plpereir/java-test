package br.com.gsw.atm.withdrawmoney;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 
 * author Pedro Luiz da Silva Pereira
 * Esta classe faz a implementacao da inicializacao da aplicacao utilizando o Spring Boot.
 */
@SpringBootApplication
public class WithdrawmoneyApplication {


	public static void main(String[] args) {
		SpringApplication.run(WithdrawmoneyApplication.class, args);
	}
	  @Bean
	  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {

	      System.out.println("Let's inspect the beans provided by Spring Boot:");

	      String[] beanNames = ctx.getBeanDefinitionNames();
	      Arrays.sort(beanNames);
	      for (String beanName : beanNames) {
	        System.out.println(beanName);
	      }

	    };
	  }
}
