package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.hibernate.SessionFactory;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
//import org.primefaces.event.






import org.primefaces.event.UnselectEvent;

import hbm.Doctores;
import hbm.Registros;
import hbm.RegistrosHome;
import hbm.SessionFact;

@ManagedBean(name="registro")
@ViewScoped
public class RegistrosController {
	private int numSemana;
	private ArrayList<ArrayList<String>> seleccion = new ArrayList<ArrayList<String>>();
	private Registros registro = new Registros();
	private List<String> diaSem;
	private ArrayList<Registros> listaReg;
	private Doctores doctor;
	
	private ArrayList<Registros> listaHorarios = new ArrayList<Registros>();
	private ArrayList<Registros> selectedHorario;
	
	
	
	@PostConstruct
    public void init() {
		diaSem = new ArrayList<String>();
		diaSem.add("Lunes");
		diaSem.add("Martes");
		diaSem.add("Miercoles");
		diaSem.add("Jueves");
		diaSem.add("Viernes");
		listaReg = new ArrayList<Registros>();
		
		doctor = (Doctores) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ObjDoc");
		for(int i=0; i<5; i++){
			Registros horario = new Registros();
			horario.setDia(diaSem.get(i));
			horario.setId(""+new Date().getTime());
			horario.setDoctores(doctor);
			listaHorarios.add(horario);
			
		}
		if(doctor==null){
			 ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
     	      try {
		 			ctx.redirect( ctx.getRequestContextPath() + "/faces/Login.xhtml" );
		 	   } catch ( IOException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		}
				
    }
	

	public ArrayList<Registros> getListaHorarios() {
		return listaHorarios;
	}



	public void setListaHorarios(ArrayList<Registros> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}



	public ArrayList<Registros> getSelectedHorario() {
		return selectedHorario;
	}



	public void setSelectedHorario(ArrayList<Registros> selectedHorario) {
		this.selectedHorario = selectedHorario;
	}

	public Registros getRegistro() {
		return registro;
	}

	public void setRegistro(Registros registro) {
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
	
	
	private boolean existeReg(ArrayList<Registros> listaReg, String dia){
		Iterator<Registros> it = listaReg.iterator();
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
        System.out.println( "fechaini: " + event.getSource());
        //Calendar c= (Calendar)event.getSource();
        //Object o = format.format( (Date)event.getObject());
        //c.setValue(o);
        
    }
	
	public void onDateSelectF(SelectEvent event) {
		if(event.getObject()==null)
			return;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println( "fechafin: " + event.getSource());
        //Calendar c= (Calendar)event.getSource();
        //c.setValue(format.format( (Date)event.getObject()));
        
    }
	
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("----------");
		 //dialogMensaje("Selected", ((Registros) event.getObject()).getDia());
	    }
	 
    public void onRowUnselect(UnselectEvent event) {
    	//dialogMensaje("Unselected", ((Registros) event.getObject()).getDia());
    }
   
    private void dialogMensaje(String titulo, String mensaje, FacesMessage.Severity tipo){
		FacesMessage message = new FacesMessage(tipo, titulo, mensaje );
		FacesContext.getCurrentInstance().addMessage(null, message);
        
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
    public void doRegistro(){
    	System.out.println("Registranado horarios "+ selectedHorario.isEmpty());
    	boolean faltan = false;
    	if(selectedHorario!=null){
    		String msg =" ";
			for(Registros s : selectedHorario){
				if(s.getFechafin()!="" && s.getFechainicio()!=""){
					SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
					System.out.println(doctor.getEmail());
					String id=String.valueOf(new Date().getTime());;
					s.setId(id);
					s.setSemana((byte)numSemana);
					s.setFecharegistro(convFechaHoy());
					RegistrosHome reg = new RegistrosHome();
					reg.attachDirty(s);
					msg+="Dia: "+s.getDia()+ " id: "+ s.getId()+"\n";
					dialogMensaje("Sus horarios han sido registrados id: "+s.getId(),msg, FacesMessage.SEVERITY_INFO);
				}else{
					faltan = true;
				}
			}
    	 if (faltan)
    		 dialogMensaje("Datos incompletos","Algunos registros no se guardaron", FacesMessage.SEVERITY_WARN);
    	}
				
    }
    
    private String convFechaHoy(){
    	SimpleDateFormat formateador = new SimpleDateFormat(
	   "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
	   Date fechaDate = new Date();
	   String fecha = formateador.format(fechaDate);
	   return fecha;
    }
}
