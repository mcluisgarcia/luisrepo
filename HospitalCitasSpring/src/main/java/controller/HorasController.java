package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CitasDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import servicio.HospitalService;

@Controller
@RequestMapping("/showDetalles")
public class HorasController {
	
private HospitalService hospitalService;
	
	@Autowired
	public HorasController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void setupForm(Model model) {
		//return "pacientes";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	private String submitForm(Model model, 
			@RequestParam("id") String id 
			){
		
		System.out.println( id );
		
		System.out.println("obteniendo reg");
		CitasDAO citas = new CitasDAO();
		List<String> detalles = (ArrayList)citas.findById(id);
		String html = "<table>";
		for(String s: detalles){
			html+=("<tr><td>"+s+"</td></tr>");
		}
		html+="</table>";
		return html;
	}

}
