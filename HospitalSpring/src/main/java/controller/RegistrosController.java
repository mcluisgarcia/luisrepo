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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import servicio.HospitalService;
import modelo.DoctorDTO;
import modelo.DropDown;
import modelo.LoginDTO;
import modelo.RegistroDAO;
import modelo.RegistroDTO;
import modelo.RegistrosDTO;

@Controller
@RequestMapping("/doctores")
public class RegistrosController {
	private int numSemana;
	private ArrayList<ArrayList<String>> seleccion = new ArrayList<ArrayList<String>>();
	private RegistroDTO registro = new RegistroDTO();
	private String today;
	private List<String> diaSem;
	private Date[] hi;
    private String[] hf;
	private ArrayList<RegistroDTO> listaReg;
	private Date h;
	private String doctor;
	
	private ArrayList<RegistroDTO> listaHorarios = new ArrayList<RegistroDTO>();
	private ArrayList<RegistroDTO> selectedHorario;
	
	
	 private HospitalService hospitalService;
		
		@Autowired
		public RegistrosController(HospitalService hospitalService) {
			this.hospitalService = hospitalService;
		}

		@RequestMapping(method = RequestMethod.GET)
		public String setupForm(Model model) {
			RegistroDTO reg = new RegistroDTO();
			model.addAttribute("registros", reg); 
		    return "Login";
		}

		@RequestMapping(method = RequestMethod.POST,consumes="application/json; charset=UTF-8")
		//public String sumbitForm(@RequestParam("registros") Object registros, HttpServletRequest request, ModelMap model) {
		public String sumbitForm(@RequestBody  RegistroDTO[] registros, HttpSession session ){
			//DoctorDTO d = (DoctorDTO)request.getSession().getServletContext().ge.getAttribute("doctor");
			DoctorDTO d = (DoctorDTO)session.getAttribute("doctor");
			
			System.out.println(d);
			RegistroDAO crud = new RegistroDAO();
			for(RegistroDTO r: registros){
				System.out.println(r.getDia());
				r.setDoctor(d.getEmail());
				r.setFechaRegistro(new Date().toString());
				r.setId(String.valueOf( new Date().getTime() ) );
				//crud.create(r);
			}
			return "doctores";
		}

		
		@ModelAttribute("semanas")//variable que se encuentra en el formulario ${sportTypes}
		public Map<String,String> populateSemanas() {
			return hospitalService.getSemanas();
		}
	
	
}
