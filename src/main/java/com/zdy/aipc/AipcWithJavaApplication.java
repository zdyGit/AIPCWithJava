package com.zdy.aipc;

import com.zdy.aipc.utils.SysUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.util.Arrays;


@SpringBootApplication
@EnableScheduling
public class AipcWithJavaApplication {

	public static void main(String[] args) {

		SpringApplication.run(AipcWithJavaApplication.class, args);

	}

}
