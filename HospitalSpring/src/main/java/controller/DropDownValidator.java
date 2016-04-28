package controller;

import modelo.DropDown;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class DropDownValidator implements Validator{


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DropDown.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		DropDown d = (DropDown)target;
		
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "semana", "required.semana");
			
		  // if("NONE".equals(d.getSemana())){
		//	errors.rejectValue("Semana", "required.semana");
		 //  }
		
	}

}
