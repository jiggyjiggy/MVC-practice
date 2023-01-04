package org.example.practice.reflection;

import org.example.practice.reflection.annotation.Controller;
import org.example.practice.reflection.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReflectionTest {
	private final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
	
	@Test
	void componentScan() {
		Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));
		logger.debug("component, beans: {}", beans);
	}
	
	@Test
	void controllerScan() {
		Set<Class<?>> beans = getTypesAnnotatedWith(Controller.class);
		logger.debug("controller, beans: {}", beans);
	}
	
	@Test
	void serviceScan() {
		Set<Class<?>> beans = getTypesAnnotatedWith(Service.class);
		logger.debug("service, beans: {}", beans);
	}
	
	private static Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
		Reflections reflections = new Reflections("org.example.practice.reflection");
		
		Set<Class<?>> beans = new HashSet<>();
		beans.addAll(reflections.getTypesAnnotatedWith(annotation));
		
		return beans;
	}
	private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
		Reflections reflections = new Reflections("org.example.practice.reflection");
		
		Set<Class<?>> beans = new HashSet<>();
		annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
		
		return beans;
	}
}
