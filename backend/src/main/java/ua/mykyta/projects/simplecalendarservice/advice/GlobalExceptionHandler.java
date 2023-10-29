package ua.mykyta.projects.simplecalendarservice.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> globalErrorHandling(final Exception e) {
		LOG.error("Something went wrong", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Something went wrong"));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> validationErrorHandling(final MethodArgumentNotValidException e) {
		final BindingResult bindingResult = e.getBindingResult();

		Map<String, String> errors = new HashMap<>();
		bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), getDefaultMessage(error)));
		bindingResult.getGlobalErrors().forEach(error -> errors.put(error.getObjectName(), getDefaultMessage(error)));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	private String getDefaultMessage(final ObjectError error) {
		return ofNullable(error).map(ObjectError::getDefaultMessage).orElse("Invalid field value");
	}
}
