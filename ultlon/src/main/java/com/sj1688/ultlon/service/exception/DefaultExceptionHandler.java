package com.sj1688.ultlon.service.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {
	@ExceptionHandler({ Exception.class })
	public ModelAndView processUnauthenticatedException(final Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", ex);
		mv.setViewName("error");
		return mv;
	}
	
}