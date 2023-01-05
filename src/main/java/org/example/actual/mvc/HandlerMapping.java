package org.example.actual.mvc;

public interface HandlerMapping {
	Object findHandler(HandlerKey handlerKey);
}
