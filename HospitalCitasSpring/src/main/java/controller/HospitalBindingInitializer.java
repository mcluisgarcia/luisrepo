package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import servicio.HospitalService;

public class HospitalBindingInitializer implements WebBindingInitializer {
	
	private HospitalService hospitalService;

	@Autowired
	public HospitalBindingInitializer( HospitalService hospitalService ) {
		// TODO Auto-generated constructor stub
		this.hospitalService = hospitalService;
	}

	
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		//binder.registerCustomEditor(SportType.class, new SportTypeEditor( 				reservationService));
	}

}
