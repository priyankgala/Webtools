package com.me.priyav.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CheckProductPriceContraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckProductPrice{
	
	//public String value();
	
	public String message() default "Email Already Exists";
	
	public Class<?>[] groups() default{};
	
	public Class<? extends Payload>[] payload() default{};

}
