package org.example.practice.reflection;

import org.example.practice.reflection.annotation.Controller;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ReflectionTest {
	private final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
	
	@Test
	void controllerScan() {
		Reflections reflections = new Reflections("org.example.practice.reflection");
		
		Set<Class<?>> beans = new HashSet<>();
		beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));  // controller들을 Bean에 등록
		
		logger.debug("beans: {}", beans);
	}
}
