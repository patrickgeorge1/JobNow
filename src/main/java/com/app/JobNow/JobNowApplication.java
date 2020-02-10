package com.app.JobNow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.app"})
@EnableJpaRepositories("com.app")
@EntityScan("com.app")
public class JobNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobNowApplication.class, args);
	}

}
