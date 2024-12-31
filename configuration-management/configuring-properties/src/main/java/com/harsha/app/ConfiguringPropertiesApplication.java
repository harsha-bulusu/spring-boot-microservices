package com.harsha.app;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

@SpringBootApplication
//@EnableConfigurationProperties(MyConfig.class)
public class ConfiguringPropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfiguringPropertiesApplication.class, args);
		Properties properties = System.getProperties();
		System.out.println(properties);
	}

}

@Component
class ReadEnvConfig{
	@Autowired
	private Environment environment;
	// All the configuration properties from various sources are loaded into this env object

	@PostConstruct
	public void init() {
		System.out.println(environment.getProperty("PATH")); // Accessing OS ENV variable
		System.out.println(environment.getProperty("server.port")); // Accessing application.yml
	}
}