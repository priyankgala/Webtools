package com.me.priyav.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckProductPriceContraintValidator implements ConstraintValidator<CheckProductPrice, Double> {

	@Override
	public boolean isValid(Double userEnteredValue, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		System.out.println("*********************Value is:" + userEnteredValue);
		boolean result = true;

		if (userEnteredValue.isNaN()) {
			return false;
		}

		return result;
	}

}