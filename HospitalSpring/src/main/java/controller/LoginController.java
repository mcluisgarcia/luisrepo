package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import modelo.DoctorDAO;
import modelo.DoctorDTO;
import modelo.LoginDAO;
import modelo.LoginDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;






import servicio.HospitalService;


@Controller
@RequestMapping("/Login")
public class LoginController {

    private HospitalService hospitalService;
	
	@Autowired
	public LoginController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute("login", new LoginDTO()); 
	    return "Login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String sumbitForm(@ModelAttribute("login") LoginDTO login, HttpServletRequest request, ModelMap model) {
		DoctorDTO doc = null;
		
		if ( login.getEmail() != null && login.getPassword() !=null ) {
			
			doc = hospitalService.login(login);
			System.out.println(doc.getEmail());
			
		}
		if(doc.getEmail() !=null ){
			request.getSession().setAttribute("doctor", doc);
			//model.addAttribute("msg","Bienvenido Dr. "+ doc.getNombre() + " "+ new Date().toString());
			request.getSession().setAttribute("msg", "Bienvenido Dr. "+ doc.getNombre() + " "+ new Date().toString());
			return "doctores";
		}
		request.getSession().setAttribute("msg", "El usuario no existe");
		
		model.addAttribute("msg", "El usuario no existe");
		return "Login";
	}
	
  

}
