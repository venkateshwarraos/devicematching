package com.device.devicematcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DeviceMatchingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceMatchingApplication.class, args);
	}

}
