package ch.enyo.springapp.whatchlist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {

	@Override
	public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
		Double number;
		try {
			number = Double.parseDouble(value.getRating());
		} catch (NumberFormatException e) {
			return true;
		}

		return !(number >= 8 &&  "L".equals(value.getPriority()));
	}

}
