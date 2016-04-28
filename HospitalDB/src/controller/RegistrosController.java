package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
//import org.primefaces.event.






import org.primefaces.event.UnselectEvent;

import modelo.RegistroDTO;

@ManagedBean(name="registro")
@SessionScoped
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
	
	
	private Long index; // +setter
	
	@PostConstruct
    public void init() {
		diaSem = new ArrayList<String>();
		diaSem.add("Lunes");
		diaSem.add("Martes");
		diaSem.add("Miercoles");
		diaSem.add("Jueves");
		diaSem.add("Viernes");
		listaReg = new ArrayList<RegistroDTO>();
		
		doctor = "";//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email").toString();
		for(int i=0; i<5; i++){
			RegistroDTO horario = new RegistroDTO();
			horario.setDia(diaSem.get(i));
			horario.setId(i);
			horario.setDoctor(doctor);
			listaHorarios.add(horario);
			
		}
		
		
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }
	
	

	public ArrayList<RegistroDTO> getListaHorarios() {
		return listaHorarios;
	}



	public void setListaHorarios(ArrayList<RegistroDTO> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}



	public ArrayList<RegistroDTO> getSelectedHorario() {
		return selectedHorario;
	}



	public void setSelectedHorario(ArrayList<RegistroDTO> selectedHorario) {
		this.selectedHorario = selectedHorario;
	}



	public void setIndex(Long index){
		this.index = index;
	}
	
	public List<String> getDiaSem(){
		return diaSem;
	}

	

	public String getToday() {		
		//System.out.println(today);
		return today;
	}



	public void setToday(String today) {
		//System.out.println(today);
		this.today = today;
	}



	public RegistroDTO getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroDTO registro) {
		this.registro = registro;
	}

	public int getNumSemana() {
		return numSemana;
	}

	public void setNumSemana(int numSemana) {
		this.numSemana = numSemana;
	}

	public ArrayList<Integer> getSemanas() {
		ArrayList<Integer> semanas = new ArrayList<Integer>();
		for(int i=1;i<=52; i++){
			semanas.add(i);
		}
		return semanas;
	}
	
	
	
	public void addDiaSel(AjaxBehaviorEvent event){
		System.out.println(today);
		System.out.println( ((SelectOneRadio)event.getSource()).getClientId() );
		
		if ( !existeReg(listaReg,today) ){
			RegistroDTO reg = new RegistroDTO();
			reg.setDia(today);
			listaReg.add(reg);
		}
	}
	
	private boolean existeReg(ArrayList<RegistroDTO> listaReg, String dia){
		Iterator<RegistroDTO> it = listaReg.iterator();
		while( it.hasNext() ){
			if( it.next().getDia().equals(dia) ){
				return true;
			}
		}
		return false;
	}

	public void onDateSelect(SelectEvent event) {
		if(event.getObject()==null)
			return;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
       // facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        //if(hi!=null)
			//for(Date s : hi){
				System.out.println(format.format( ((Date)event.getObject()) ));
				if(listaReg!=null)
				for(RegistroDTO s : listaHorarios){
					System.out.println(s.getDia());
				}
				//Hi[0]=;
			//}
    }
	
	public Date getH() {
		return h;
	}

	public void setH(Date h) {
		this.h = h;
	}

	public Date[] getHi() {
		return hi;
	}

	public void setHi(Date[] hi) {
		this.hi = hi;
	}

	public String[] getHf() {
		return hf;
	}

	public void setHf(String[] hf) {
		this.hf = hf;
	}
	
	 public void onRowSelect(SelectEvent event) {
		 dialogMensaje("Selected", ((RegistroDTO) event.getObject()).getDia());
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	    	dialogMensaje("Unselected", ((RegistroDTO) event.getObject()).getDia());
	    }
	    	    private void dialogMensaje(String titulo, String mensaje){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje );
			FacesContext.getCurrentInstance().addMessage(null, message);
	        
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	
}
