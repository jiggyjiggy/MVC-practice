package org.example.actual.mvc;

import org.example.actual.mvc.controller.Controller;
import org.example.actual.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
	// key      -> /users (uriPath)
	// value    -> UserController
	private Map<String, Controller> mappings = new HashMap<>();
	
	void init() {
		mappings.put("/", new HomeController());
	}
	
	public Controller findHandler(String uriPath) {
		return mappings.get(uriPath);
	}
}
