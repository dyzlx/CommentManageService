package com.dyz.commentservice.api.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dyz.commentservice.api.model.Result;
import com.dyz.commentservice.common.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionInterceptor {

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<Result> handlerBusinessException(BusinessException exception) {
		log.error("commentservice catch business exception", exception);
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(Result.builder().code(exception.getCode()).message(exception.getMessage()).build());
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Result> handlerException(Exception exception) {
		log.error("commentservice catch undefined exception", exception);
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(Result.builder().code(-1).message(exception.getMessage()).build());
	}

}

