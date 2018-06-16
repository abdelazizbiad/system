package org.ensah;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration(exclude = { WebMvcAutoConfiguration.class })
//@ComponentScan("com.fasterxml.jackson.databind.Module")
@SpringBootApplication
public class SysEcoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysEcoleApplication.class, args);
	}
	
}
