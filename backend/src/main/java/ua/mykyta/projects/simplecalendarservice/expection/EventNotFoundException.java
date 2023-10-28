package ua.mykyta.projects.simplecalendarservice.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {
	public EventNotFoundException(final long eventId) {
		super(format("There is no event with id '%s'", eventId));
	}
}
