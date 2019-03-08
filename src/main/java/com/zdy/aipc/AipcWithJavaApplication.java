package com.zdy.aipc;

import com.zdy.aipc.utils.SysUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;


@SpringBootApplication
public class AipcWithJavaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AipcWithJavaApplication.class, args);
		//配置项目根目录
		String confPath = context.getEnvironment().getProperty("cpath");

		if(confPath == null ||confPath.isEmpty()){
			System.out.println("command:--cpath to set config rootpath");
			return ;
		}
		else{
			System.out.println("set config rootpath success:"+confPath);
			SysUtils.setSysRootPath(confPath);
		}
	}

}
