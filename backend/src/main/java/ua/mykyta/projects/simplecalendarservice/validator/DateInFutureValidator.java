package ua.mykyta.projects.simplecalendarservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;
import static java.util.Optional.ofNullable;

public class DateInFutureValidator implements ConstraintValidator<DateInFuture, LocalDateTime> {
	@Override
	public void initialize(final DateInFuture constraintAnnotation) {
	}

	@Override
	public boolean isValid(final LocalDateTime date, final ConstraintValidatorContext context) {
		return ofNullable(date).map(LocalDateTime.now()::isBefore).orElse(FALSE);
	}
}
