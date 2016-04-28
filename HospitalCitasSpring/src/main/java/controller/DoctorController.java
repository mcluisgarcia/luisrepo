package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import servicio.HospitalService;
import modelo.ComboboxM;
import modelo.DoctorDAO;
import modelo.DoctorDTO;
import modelo.LoginDTO;
import modelo.RegistroDAO;
import modelo.RegistroDTO;


@Controller
@RequestMapping("/saveDoc")
@SessionAttributes("doctor")
public class DoctorController implements Serializable{
	private HospitalService hospitalService;
	
	@Autowired
	public DoctorController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void setupForm(Model model) {
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView sumbitForm(@RequestParam("nombre") String nombre, 
			@RequestParam("email") String email, 
			@RequestParam("edad") String edad, 
			@RequestParam("password") String password, 
			@RequestParam("sexo") String sexo, 
			@RequestParam("identificacion") String identificacion, 
			@RequestParam("especialidad") String especialidad, 
			HttpSession session,
			HttpServletResponse httpServletResponse,
			SessionStatus status){

		DoctorDAO crud = new DoctorDAO();
		DoctorDTO doc = new DoctorDTO();
		doc.setEdad(edad);
		doc.setEmail(email);
		doc.setEspecialidad(especialidad);
		doc.setIdentificacion(identificacion);
		doc.setNombre(nombre);
		doc.setPassword(password);
		doc.setSexo(sexo);
		crud.create(doc);
		System.out.println("redireccionando");
		session.setAttribute("doctor", doc);
		session.setAttribute("msg", "Bienvenido Dr. "+ doc.getNombre() + " "+ new Date().toString());
		status.setComplete();
		
		//return "doctores";
		//httpServletResponse.setHeader("Location", "/doctores");
		return new ModelAndView("redirect:" + "doctores");//"redirect:doctores";
	}

@ModelAttribute("sexos")
public Map referenceData(HttpServletRequest request) throws Exception {
		
		Map referenceData = new HashMap();
		List<String> sexos = new ArrayList<String>();
		sexos.add("hombre");
		sexos.add("mujer");
		
		referenceData.put("sexos", sexos);
		
		return referenceData;
	}

}
