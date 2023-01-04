package org.example.practice.front_controller_pattern.mvc;

import org.example.practice.front_controller_pattern.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
	private RequestMappingHandlerMapping rmhm;
	
	@Override
	public void init() throws ServletException {
//		super.init();
		rmhm = new RequestMappingHandlerMapping();
		rmhm.init();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		super.service(req, resp);
		log.info("[DispatcherServlet] service start");
		try {
			Controller handler = rmhm.findHandler(request.getRequestURI());
			String viewName = handler.handleRequest(request, response);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			log.error("exception occurred: {}", e.getMessage(), e);
			throw new ServletException(e);
		}
	}
}
