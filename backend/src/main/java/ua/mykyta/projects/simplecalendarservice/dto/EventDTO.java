package ua.mykyta.projects.simplecalendarservice.dto;

import ua.mykyta.projects.simplecalendarservice.validator.DateRange;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@DateRange
public class EventDTO {
	private long id;

	@NotBlank(message = "Event title should not be blank")
	private String title;

	private String description;

	@NotNull(message = "Start date of event is absent")
	private LocalDateTime startDate;

	@NotNull(message = "End date of event is absent")
	private LocalDateTime endDate;

	private String location;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(final LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}
}
