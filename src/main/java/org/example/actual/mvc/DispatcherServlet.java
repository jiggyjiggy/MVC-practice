package org.example.actual.mvc;

import org.example.actual.mvc.controller.Controller;
import org.example.actual.mvc.view.JspViewResolver;
import org.example.actual.mvc.view.ModelAndView;
import org.example.actual.mvc.view.View;
import org.example.actual.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
	private RequestMappingHandlerMapping rmhm;
	private List<HandlerAdapter> handlerAdapters;
	private List<ViewResolver> viewResolvers;
	
	@Override
	public void init() throws ServletException {
//		super.init();
		rmhm = new RequestMappingHandlerMapping();
		rmhm.init();
		
		handlerAdapters = List.of(new SimpleControllerHandlerAdapter());
		viewResolvers = Collections.singletonList(new JspViewResolver());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		super.service(req, resp);
		log.info("[DispatcherServlet] service start");
		try {
			Controller handler = rmhm.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI()));
			
			HandlerAdapter handlerAdapter = handlerAdapters.stream()
				.filter(ha -> ha.supports(handler))
				.findFirst()
				.orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));
			
			ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);
			
			for (ViewResolver viewResolver : viewResolvers) {
				View view = viewResolver.resolveView(modelAndView.getViewName());
				view.render(modelAndView.getModel(), request, response);
			}
			
		} catch (Exception e) {
			log.error("exception occurred: {}", e.getMessage(), e);
			throw new ServletException(e);
		}
	}
}
