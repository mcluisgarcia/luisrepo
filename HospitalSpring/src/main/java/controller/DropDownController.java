package controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import modelo.DropDown;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class DropDownController extends SimpleFormController implements Controller{

	public DropDownController() {
		// TODO Auto-generated constructor stub
		setCommandClass(DropDown.class);
		setCommandName("semanaForm");
	}
	
	   @Override
	   protected Object formBackingObject(HttpServletRequest request)
	                     throws Exception {
		   DropDown drop = new DropDown();  //super.formBackingObject(request);
		   drop.setSemana("1");
	     // rantForm.setVehicle(new Vehicle());
	      return drop;
	   }

	   @Override
	   protected Map referenceData(HttpServletRequest request) throws Exception {
	      
		   Map referenceData = new HashMap();
	    	Map<String,String> semanas = new LinkedHashMap<String,String>();
	    	for(int i=0; i<53; i++){
				semanas.put(""+i, ""+i);
			}
	    	referenceData.put("semanas", semanas);
	    	
	    	return referenceData;
		   /*Map referenceData = new HashMap();

	      referenceData.put("language", LANGUAGES);

	      return referenceData;*/
	   }

	   @Override
	   protected ModelAndView onSubmit(Object command, BindException bindException)
	                     throws Exception {
		   DropDown rss = (DropDown) command;
	      
	      return new ModelAndView("Success","semana",rss);
	   }
	   
	   

}
