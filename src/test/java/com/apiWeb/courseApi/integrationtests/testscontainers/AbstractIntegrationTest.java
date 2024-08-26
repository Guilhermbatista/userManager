package com.apiWeb.courseApi.integrationtests.testscontainers;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.aot.AotApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

	public class Initializer implements AotApplicationContextInitializer<ConfigurableApplicationContext> {
		static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:9.0.1");

		private static void startContainers() {
			Startables.deepStart(Stream.of(mysql)).join();
		}

		private static Map<String, String> createConnectionConfiguration() {
			return Map.of("spring.datasource.datasource.url", mysql.getJdbcUrl(),
					"spring.datasource.datasource.username", mysql.getUsername(),
					"spring.datasource.datasource.password", mysql.getPassword());

		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			startContainers();
			ConfigurableEnvironment environment = applicationContext.getEnvironment();
			MapPropertySource testcontainers = new MapPropertySource("testconatiners",
					(Map) createConnectionConfiguration());
			environment.getPropertySources().addFirst(testcontainers);
		}

	}

}
