package org.example.practice.reflection;

import org.example.practice.reflection.annotation.Controller;
import org.example.practice.reflection.annotation.Service;
import org.example.practice.reflection.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

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
	
	@DisplayName("class의 내부에 접근할 수 있다.")
	@Test
	void showInsideOfClass() {
		Class<User> clazz = User.class;
		
		logger.debug(clazz.getName());
		
		logger.debug("User all declared fields: {}", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
		logger.debug("User all declared constructors: {}", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
		logger.debug("User all declared methods: {}", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
	}
	
	@DisplayName("heap 영역에 load되어 있는 class type object를 가져오는 3가지 방법이 있다")
	@Test
	void loadObjectOfClassTypeOnHeap() throws ClassNotFoundException {
		// 첫번째 방법
		Class<User> clazz = User.class;
		
		// 두번째 방법
		User user = new User("jiggyjiggy", "기성");
		Class<? extends User> clazz2 = user.getClass();
		
		// 세번째 방법
		Class<?> clazz3 = Class.forName("org.example.practice.reflection.model.User");
		
		logger.debug("clazz: {}", clazz);
		logger.debug("clazz2: {}", clazz2);
		logger.debug("clazz3: {}", clazz3);
		
		assertThat(clazz == clazz2).isTrue();
		assertThat(clazz2 == clazz3).isTrue();
		assertThat(clazz3 == clazz).isTrue();
	}
}
