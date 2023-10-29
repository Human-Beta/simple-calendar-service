package ua.mykyta.projects.simplecalendarservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DateInFutureValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateInFuture {
	String message() default "Date should be in the future";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
