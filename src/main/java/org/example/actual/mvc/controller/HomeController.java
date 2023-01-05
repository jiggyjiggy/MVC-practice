package org.example.actual.mvc.controller;

import org.example.actual.mvc.RequestMethod;
import org.example.actual.mvc.annotation.Controller;
import org.example.actual.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "home";
	}
}
