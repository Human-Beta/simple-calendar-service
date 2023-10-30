package ua.mykyta.projects.simplecalendarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.mykyta.projects.simplecalendarservice.data.EventData;
import ua.mykyta.projects.simplecalendarservice.dto.EventDTO;
import ua.mykyta.projects.simplecalendarservice.facade.EventFacade;
import ua.mykyta.projects.simplecalendarservice.service.impl.MapperService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/events")
public class EventController {

	private final MapperService mapperService;
	private final EventFacade eventFacade;

	@Autowired
	public EventController(final MapperService mapperService, final EventFacade eventFacade) {
		this.mapperService = mapperService;
		this.eventFacade = eventFacade;
	}

	@GetMapping
	public List<EventDTO> getEvents() {
		return toDTOs(eventFacade.getEvents());
	}

	private List<EventDTO> toDTOs(final List<EventData> events) {
		return mapperService.mapAll(events, EventDTO.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EventDTO addEvent(@RequestBody @Valid final EventDTO event) {
		final EventData eventData = toData(event);

		return toDTO(eventFacade.saveEvent(eventData));
	}

	private EventData toData(final EventDTO event) {
		return mapperService.map(event, EventData.class);
	}

	private EventDTO toDTO(final EventData event) {
		return mapperService.map(event, EventDTO.class);
	}

	@GetMapping("/{id}")
	public EventDTO findEvent(@PathVariable final long id) {
		return toDTO(eventFacade.findEvent(id));
	}

	@PutMapping("/{id}")
	public EventDTO updateEvent(@PathVariable final long id, @RequestBody @Valid final EventDTO event) {
		final EventData eventData = toData(event);
		eventData.setId(id);

		return toDTO(eventFacade.updateEvent(eventData));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEvent(@PathVariable final long id) {
		eventFacade.deleteEvent(id);
	}
}
