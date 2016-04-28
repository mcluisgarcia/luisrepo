package controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import servicio.HospitalService;
import modelo.ComboboxM;
import modelo.DoctorDTO;
import modelo.LoginDTO;
import modelo.RegistroDAO;
import modelo.RegistroDTO;

@Controller
@RequestMapping("/doctores")
@SessionAttributes("combobox")
public class RegistrosController extends SimpleFormController{
	
	 private HospitalService hospitalService;
		
		@Autowired
		public RegistrosController(HospitalService hospitalService) {
			this.hospitalService = hospitalService;
		}
		
		@ModelAttribute("semanas")
		public List<ComboboxM> populateSemanas() {
		return hospitalService.getSemanas();
		}
		
		//@ModelAttribute("combobox")
		public ComboboxM newCombobox() {
			return new ComboboxM();
		}
		
		

		@RequestMapping(method = RequestMethod.GET)
		public String setupForm(Model model, HttpSession session) {
			if(session.getAttribute("doctor")==null || session.getAttribute("doctor")=="")
				return "redirect:Login";
			model.addAttribute("combobox", new ComboboxM()); 
		    return "doctores";
		}
		
		@RequestMapping(value="{salir}", method = RequestMethod.GET)
		public String exitForm(@PathVariable String salir, HttpSession session) {
			 session.invalidate();
		   return "redirect:/Login";
		}

		@RequestMapping(method = RequestMethod.POST,consumes="application/json; charset=UTF-8")
		public String sumbitForm(@RequestBody  RegistroDTO[] registros, 
				HttpSession session, 
				@ModelAttribute("combobox") ComboboxM combo){
			DoctorDTO d = (DoctorDTO)session.getAttribute("doctor");
			
			System.out.println(d);
			RegistroDAO crud = new RegistroDAO();
			for(RegistroDTO r: registros){
				String id = String.valueOf( new Date().getTime() );
				System.out.println(r.getDia()+id);
				r.setDoctor(d.getEmail());
				r.setFechaRegistro(new Date().toString());
				r.setId(id);
				crud.create(r);
			}
			return "doctores";
		}
	
}
