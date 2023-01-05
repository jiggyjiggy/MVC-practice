package org.example.actual.mvc;

import org.example.actual.mvc.controller.Controller;
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
			Controller handler = rmhm.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI()));
			// [issue 2] spring web MVC framework에 비교했을때, handler에 대해 바로 실행하고있다
			// Handler adapter를 통해야한다
			
			// [issue 1] redirect:/users <- 처리되고있지 않음
			String viewName = handler.handleRequest(request, response);
			// [issue 3] spring web MVC framework에 비교했을때, view에 대한 판단 과정이 없다
			// View resolver를 통해서 redirect인지 forward인지 확인하는 과정이 필요하다
			
			// [issue 1] forward 만 처리한 코드
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			log.error("exception occurred: {}", e.getMessage(), e);
			throw new ServletException(e);
		}
	}
}
