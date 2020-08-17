package ch.enyo.springapp.whatchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<Priority, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	
		return value.trim().length()==1 && "LMN".contains(value.toUpperCase());
	}


}
