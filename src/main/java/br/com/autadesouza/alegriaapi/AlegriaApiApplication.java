package br.com.autadesouza.alegriaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan
@ComponentScan
@EnableTransactionManagement
public class AlegriaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlegriaApiApplication.class, args);
	}

}
