package ua.mykyta.projects.simplecalendarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.mykyta.projects.simplecalendarservice.expection.EventNotFoundException;
import ua.mykyta.projects.simplecalendarservice.model.Event;
import ua.mykyta.projects.simplecalendarservice.repository.EventRepository;
import ua.mykyta.projects.simplecalendarservice.service.EventService;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;

	@Autowired
	public EventServiceImpl(final EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public List<Event> getEvents() {
		return eventRepository.findAll();
	}

	@Override
	@Transactional
	public Event saveEvent(final Event event) {
		return eventRepository.save(event);
	}

	@Override
	public Optional<Event> findEvent(final long id) {
		return eventRepository.findById(id);
	}

	@Override
	@Transactional
	public Optional<Event> updateEvent(final Event event) {
		return findEvent(event.getId()).map((foundEvent) -> {
			merge(event, foundEvent);

			return eventRepository.save(foundEvent);
		});
	}

	private static void merge(final Event source, final Event target) {
		target.setTitle(source.getTitle());
		target.setDescription(source.getDescription());
		target.setStartDate(source.getStartDate());
		target.setEndDate(source.getEndDate());
		target.setLocation(source.getLocation());
	}

	@Override
	@Transactional
	public boolean deleteEvent(final long id) {
		return findEvent(id).map(event -> {
			eventRepository.delete(event);
			return TRUE;
		}).orElse(FALSE);
	}
}
