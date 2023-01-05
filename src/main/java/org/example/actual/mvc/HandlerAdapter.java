package org.example.actual.mvc;

import org.example.actual.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
	boolean supports(Object handler);
	
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
