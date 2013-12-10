package com.scrumretro.web.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * 
 * @author Sanju Thomas
 *
 */

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ConfirmationPasswordValidator.class)
@Documented
public @interface ValidateConfirmationPassword {

}
