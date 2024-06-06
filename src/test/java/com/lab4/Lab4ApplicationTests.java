package com.lab4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(properties = { "debug=true" })
@TestPropertySource(locations = "classpath:application-test.properties")
public class Lab4ApplicationTests {

	@Test
	void contextLoads() {

	}
}