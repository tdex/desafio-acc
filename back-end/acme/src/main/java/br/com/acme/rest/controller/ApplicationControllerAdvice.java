package br.com.acme.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.acme.rest.ApiErros;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleMethodNotValidException(MethodArgumentNotValidException exception) {
		List<String> errors = exception.getBindingResult()
				.getAllErrors()
				.stream()
				.map(erro -> erro.getDefaultMessage())
				.collect(Collectors.toList());

		return new ApiErros(errors);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleResponseStatusException(ResponseStatusException exception) {
		return new ApiErros(exception.getReason());
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		return new ApiErros("Metodo não suportado pela aplicação.");
	}
}
