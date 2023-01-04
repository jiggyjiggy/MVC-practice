package org.example.practice.reflection.controller;

import org.example.practice.reflection.annotation.Controller;
import org.example.practice.reflection.annotation.RequestMapping;
import org.example.practice.reflection.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HealthCheckController {
	
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health(HttpServletRequest request, HttpServletResponse response) {
		return "ok";
	}
}
