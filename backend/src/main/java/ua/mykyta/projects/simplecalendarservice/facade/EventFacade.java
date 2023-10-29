package ua.mykyta.projects.simplecalendarservice.facade;

import ua.mykyta.projects.simplecalendarservice.data.EventData;

import java.util.List;

public interface EventFacade {

	List<EventData> getEvents();

	EventData saveEvent(EventData event);

	EventData findEvent(long id);

	EventData updateEvent(EventData eventData);

	void deleteEvent(long id);
}
