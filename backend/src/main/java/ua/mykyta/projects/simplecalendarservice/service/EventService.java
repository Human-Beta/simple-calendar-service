package ua.mykyta.projects.simplecalendarservice.service;

import ua.mykyta.projects.simplecalendarservice.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
	List<Event> getEvents();

	Event saveEvent(Event event);

	Optional<Event> findEvent(long id);

	Optional<Event> updateEvent(Event event);

	boolean deleteEvent(long id);
}
