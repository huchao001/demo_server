package com.hzz.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:jdbc.properties"})
public class DruidProperties {

	@Value("${ds.driverClassName}")
	private String driverClassName;
	@Value("${ds.url}")
	private String url;
	@Value("${ds.username}")
	private String username;
	@Value("${ds.password}")
	private String password;

	@Value("${jdbc.max_active}")
	private int maxActive;
	@Value("${jdbc.initial_size}")
	private int initialSize;
	@Value("${jdbc.max_wait}")
	private long maxWait;
	@Value("${jdbc.min_idle}")
	private int minIdle;
	@Value("${jdbc.test-while-idle}")
	private boolean testWhileIdle;
	@Value("${jdbc.validation-query}")
	private String validationQuery;
	@Value("${jdbc.connectionProperties}")
	private String connectionProperties;


	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public String getConnectionProperties() {
		return connectionProperties;
	}
}
