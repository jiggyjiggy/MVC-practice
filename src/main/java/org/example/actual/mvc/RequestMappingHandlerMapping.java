package org.example.actual.mvc;

import org.example.actual.mvc.controller.Controller;
import org.example.actual.mvc.controller.ForwardController;
import org.example.actual.mvc.controller.HomeController;
import org.example.actual.mvc.controller.UserCreateController;
import org.example.actual.mvc.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {
	private Map<HandlerKey, Controller> mappings = new HashMap<>();
	
	void init() {
//		mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
		mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
		mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
		mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
	}
	
	public Controller findHandler(HandlerKey handlerKey) {
		return mappings.get(handlerKey);
	}
}
