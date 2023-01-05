package org.example.actual.mvc;

import org.example.actual.mvc.annotation.Controller;
import org.example.actual.mvc.annotation.RequestMapping;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {
	private Map<HandlerKey, AnnotationHandler> handers = new HashMap<>();
	private Object[] basePackage;
	
	public AnnotationHandlerMapping(Object... basePackage) {
		this.basePackage = basePackage;
	}
	
	public void initialize() {
		Reflections reflections = new Reflections(basePackage);
		
		// HomeController
		Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);
		
		clazzesWithControllerAnnotation.forEach(clazz ->
			Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
				RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);
				
				// @RequestMapping(value = "/", method = RequestMethod.GET)
				Arrays.stream(getRequestMethods(requestMapping)).forEach(requestMethod ->
					handers.put(new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethod))
				);
			})
		);
	}
	
	private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
		return requestMapping.method();
	}
	
	@Override
	public Object findHandler(HandlerKey handlerKey) {
		return handers.get(handlerKey);
	}
}
