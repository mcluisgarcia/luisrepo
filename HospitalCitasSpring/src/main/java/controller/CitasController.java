package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.CitasDAO;
import modelo.CitasDTO;
import modelo.ComboboxM;
import modelo.DoctorDAO;
import modelo.DoctorDTO;
import modelo.LoginDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.SimpleFormController;

import servicio.HospitalService;

@Controller
@RequestMapping("/pacientes")
public class CitasController extends SimpleFormController {

	private HospitalService hospitalService;
	
	@Autowired
	public CitasController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}
	
	@ModelAttribute("citas")
	public CitasDTO getObjetoCitas(){
		return new CitasDTO();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void setupForm(Model model) {
		//return "pacientes";
	}

	@ModelAttribute("sexos")
	public List<String> getSexos() {
		List<String> sexos = new ArrayList<String>();
		sexos.add("Hombre");
		sexos.add("Mujer");
	return sexos;
	}
	
	@ModelAttribute("doctores")
	public Map<String,String> getDoctores() {
		Map<String, String> doctores = new HashMap<String,String>();
		System.out.println("obteniendo docs");
		DoctorDAO docs = new DoctorDAO();
		List<DoctorDTO> listaDocs = docs.findAll();
		
		for(DoctorDTO doc : listaDocs){
			System.out.println(doc.getNombre());
			doctores.put(doc.getEmail(), doc.getNombre());
		}
		
		return doctores;
	}
	
		
	public void onFormChange(HttpServletRequest request,
            HttpServletResponse response,
            Object command){
		
		System.out.println("cambio");
	}
	
	@RequestMapping(value="{email}/{semana}/{dia}", method = RequestMethod.POST)
	@ResponseBody
	private String getHoras(Model model, @PathVariable String email, 
			@PathVariable String semana, 
			@PathVariable String dia
			) {
		
		System.out.println( email + semana + dia);
		//Map<String, String> horas = new HashMap<String,String>();
		System.out.println("obteniendo reg");
		CitasDAO citas = new CitasDAO();
		List<String> listaH = null;
		List<Map<String,String>> listaHorarios = citas.findHoras(semana, email, dia);
		
		String html = (listaHorarios.size()>0)?"<option value=''>--- Seleccione un hora ---</option>":"<option value=''>--- No hay horas disponible ---</option>";
		
		if( listaHorarios.size()>0){
			
			ArrayList<Map<String, String>> hrsOcupadas = citas.findHorasOcupadas(email);	

			listaH = getlistHoras( hrsOcupadas, listaHorarios.get(0).get("fechainicio"), listaHorarios.get(0).get("fechafin"));
			buscarHrs(hrsOcupadas, listaH);
			for(String hr: listaH){
				html+="<option value='"+hr+"'>" + hr+"</option>";
				System.out.println( html );
			}
			
		}
		model.addAttribute("horasT",listaH);
		String fechaReg = semana + " " + dia;
		model.addAttribute("fechaRegistro", fechaReg);
		return html;
	}
	
	private boolean buscarHrs(ArrayList<Map<String, String>> hrsOcupadas, List<String> hrs){
		boolean res = false;
		for(Map<String, String> m: hrsOcupadas){
			Iterator it = m.entrySet().iterator();
		    while (it.hasNext()) {
		    	res = m.containsValue(hrs);
		        Map.Entry pair = (Map.Entry)it.next();
		        hrs.remove(pair.getValue());
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		}
		return res;
	}
	
	private List<String> getlistHoras(ArrayList<Map<String, String>> hrsOcupadas, String horai, String horaf){
			
		  List<String> listaH = new ArrayList<String>();
		  String ampm1 = horai.replaceAll(".[\\W\\d]","").trim();
		  String hri = horai.replaceAll("\\D","");      
		  String ampm2 = horaf.replaceAll(".[\\W\\d]","").trim();
		  String hrf = horaf.replaceAll("\\D",""); 
		  
		  int ini = Integer.valueOf(hri);
		  int fin = Integer.valueOf(hrf);
	      
	      if(ampm1.equals("pm")){
	           ini+=1200;                
	      }  
	      
	      if(ampm2.equals("pm")){
	          fin+=1200;
	      }

	      String apm ="";
	      if (ini < fin){          
	              for(int i = ini ;i<fin; i+=100){  
	            	  apm=(i>1159)?" PM":" AM";
	            	  String hrs = convNumTime(i)+apm;
	            	  	listaH.add( hrs );	              
	            }  
	      } else if (ini > fin){
	          int tmp = fin+2400;
	          apm=" AM";
	          for(int i = ini ;i<tmp; i+=100){
	               int tmp2 = i;
	               apm=(i>1159)?" PM":" AM";
	               if(tmp2 >=2400){
	            	   tmp2-=2400;
	            	   apm=(tmp2>1159)?" PM":" AM";
	               }
	               String hrs = convNumTime(tmp2)+apm;
	               		listaH.add( hrs );
	            }       
	      }
	     
		return listaH;
	}
	
	private String convNumTime(int num){
        String n = String.valueOf(num);
        if(n.equals("0")) n="0000";
        String re = (n.length()==4)?n.substring(0,2)+":"+n.substring(n.length()-2):
                                    n.substring(0,1)+":"+n.substring(n.length()-2);
        return re;
    }
	
	@RequestMapping(value="{email}/{semana}", method = RequestMethod.POST)//@ModelAttribute("horarios")
	@ResponseBody
	private String getDias(Model model, @PathVariable String email, @PathVariable String semana
			) {
		System.out.println( email + semana);
		Map<String, String> dias = new HashMap<String,String>();
		System.out.println("obteniendo reg");
		CitasDAO citas = new CitasDAO();
		List<Map<String,String>> listaHorarios = citas.findDias(semana, email);
		String html = (listaHorarios.size()>0)?"<option value=''>--- Seleccione un dia ---</option>":"<option value=''>--- No hay dias disponible ---</option>";
		for(int i = 0;i<listaHorarios.size(); i++){
			Map<String, String> temp = listaHorarios.get(i);
			//horarios.put(temp.get("idreg"), temp.get("dia") + temp.get("semana") );
			html+="<option value='"+temp.get("key")+"'>Dia " + temp.get("valor")+"</option>";
			System.out.println( html );
		}
		model.addAttribute("dias",dias);
		return html;
	}
	
	@RequestMapping(value="{email}", method = RequestMethod.POST)//@ModelAttribute("horarios")
	@ResponseBody
	private String getSemanas(Model model, @PathVariable String email) {
		
		System.out.println( email);
		Map<String, String> horarios = new HashMap<String,String>();
		System.out.println("obteniendo reg");
		CitasDAO citas = new CitasDAO();
		List<Map<String,String>> listaHorarios = citas.findSemanas(email);
		String html = (listaHorarios.size()>0)?"<option value=''>--- Seleccione una Semana ---</option>":"<option value=''>--- No hay Semanas disponible ---</option>";
		for(int i = 0;i<listaHorarios.size(); i++){
			Map<String, String> temp = listaHorarios.get(i);
			//horarios.put(temp.get("idreg"), temp.get("dia") + temp.get("semana") );
			html+="<option value='"+temp.get("key")+"'>Semana " + temp.get("valor")+"</option>";
			System.out.println( html );
		}
		model.addAttribute("horarios",horarios);
		return html;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String sumbitForm(@ModelAttribute("citas") CitasDTO citas, 
			HttpServletRequest request, 
			HttpSession session,
			SessionStatus status){

		
		CitasDAO crud = new CitasDAO();
		citas.setIdcita( String.valueOf(new Date().getTime() ) );
		System.out.println(citas.getDoctorEmail());
		System.out.println(citas.getEdad());
		System.out.println(citas.getEmail());
		System.out.println(citas.getFechaCita());
		System.out.println(citas.getIdcita());
		System.out.println(citas.getNombre());
		System.out.println(citas.getSexo());
		System.out.println(citas.getSintomas());
		System.out.println(citas.getTelefono());
		crud.create(citas);
		
		status.setComplete();
			
		return "redirect:Login";
	}

}
