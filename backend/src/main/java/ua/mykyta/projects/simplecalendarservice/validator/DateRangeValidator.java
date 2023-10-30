package ua.mykyta.projects.simplecalendarservice.validator;

import ua.mykyta.projects.simplecalendarservice.dto.EventDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateRangeValidator implements ConstraintValidator<DateRange, EventDTO> {
	@Override
	public void initialize(final DateRange constraintAnnotation) {
	}

	@Override
	public boolean isValid(final EventDTO event, final ConstraintValidatorContext context) {
		final LocalDateTime startDate = event.getStartDate();
		final LocalDateTime endDate = event.getEndDate();

		return startDate != null && endDate != null && (endDate.isAfter(startDate) || startDate.isEqual(endDate));
	}
}
