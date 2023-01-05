package org.example.actual.mvc;

import org.example.actual.mvc.controller.Controller;
import org.example.actual.mvc.controller.HomeController;
import org.example.actual.mvc.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
	// key      -> /users (uriPath)
	// value    -> UserController
	private Map<String, Controller> mappings = new HashMap<>();
	
	void init() {
		mappings.put("/", new HomeController());
		mappings.put("/users", new UserListController());
	}
	
	public Controller findHandler(String uriPath) {
		return mappings.get(uriPath);
	}
}
