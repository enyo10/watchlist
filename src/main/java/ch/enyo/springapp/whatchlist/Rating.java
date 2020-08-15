package ch.enyo.springapp.whatchlist;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = RatingValidator.class)
public @interface Rating {

	String message() default "Must be between 5.0 and 10.0";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
