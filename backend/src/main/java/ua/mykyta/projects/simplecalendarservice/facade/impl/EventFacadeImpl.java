package ua.mykyta.projects.simplecalendarservice.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mykyta.projects.simplecalendarservice.data.EventData;
import ua.mykyta.projects.simplecalendarservice.expection.EventNotFoundException;
import ua.mykyta.projects.simplecalendarservice.facade.EventFacade;
import ua.mykyta.projects.simplecalendarservice.model.Event;
import ua.mykyta.projects.simplecalendarservice.service.EventService;
import ua.mykyta.projects.simplecalendarservice.service.impl.MapperService;

import java.util.List;

@Component
public class EventFacadeImpl implements EventFacade {

	private final MapperService mapperService;
	private final EventService eventService;

	@Autowired
	public EventFacadeImpl(final MapperService mapperService, final EventService eventService) {
		this.mapperService = mapperService;
		this.eventService = eventService;
	}

	@Override
	public List<EventData> getEvents() {
		return toDataList(eventService.getEvents());
	}

	private List<EventData> toDataList(List<Event> events) {
		return mapperService.mapAll(events, EventData.class);
	}

	@Override
	public EventData saveEvent(final EventData event) {
		final Event eventModel = toModel(event);

		return toData(eventService.saveEvent(eventModel));
	}

	private Event toModel(final EventData event) {
		return mapperService.map(event, Event.class);
	}

	private EventData toData(Event event) {
		return mapperService.map(event, EventData.class);
	}

	@Override
	public EventData findEvent(final long id) {
		return eventService.findEvent(id)
				.map(this::toData)
				.orElseThrow(() -> new EventNotFoundException(id));
	}

	@Override
	public EventData updateEvent(final EventData eventData) {
		final Event event = toModel(eventData);

		return eventService.updateEvent(event)
				.map(this::toData)
				.orElseThrow(() -> new EventNotFoundException(event.getId()));
	}

	@Override
	public void deleteEvent(final long id) {
		if (!eventService.deleteEvent(id)) {
			throw new EventNotFoundException(id);
		}
	}
}
