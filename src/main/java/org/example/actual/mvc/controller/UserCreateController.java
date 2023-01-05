package org.example.actual.mvc.controller;

import org.example.actual.mvc.model.User;
import org.example.actual.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserRepository.save(new User(request.getParameter("userId"), request.getParameter("name")));
		return "redirect:/users";
	}
}
