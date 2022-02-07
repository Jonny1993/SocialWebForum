package dao;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Error");
	}

}
