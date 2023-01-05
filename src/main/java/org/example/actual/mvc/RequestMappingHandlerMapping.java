package org.example.actual.mvc;

import org.example.actual.mvc.controller.Controller;
import org.example.actual.mvc.controller.HomeController;
import org.example.actual.mvc.controller.UserCreateController;
import org.example.actual.mvc.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
	private Map<HandlerKey, Controller> mappings = new HashMap<>();
	
	void init() {
		mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
		mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
		mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
	}
	
	public Controller findHandler(HandlerKey handlerKey) {
		return mappings.get(handlerKey);
	}
}