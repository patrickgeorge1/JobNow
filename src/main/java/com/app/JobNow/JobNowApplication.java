package com.app.JobNow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.app"})
public class JobNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobNowApplication.class, args);
	}

}
