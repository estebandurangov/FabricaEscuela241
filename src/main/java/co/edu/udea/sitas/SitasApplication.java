package co.edu.udea.sitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitasApplication.class, args);
	}

}
