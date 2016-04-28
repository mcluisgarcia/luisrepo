package controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hbm.DoctoresHome;
import hbm.Doctores;


@ManagedBean(name="doctor")
@RequestScoped
public class DoctorController implements Serializable{
	public Doctores doctor = new Doctores();
	
	private String msg = "";
	private DoctoresHome crud = new DoctoresHome();
	
	
	public Doctores getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctores doctor) {
		this.doctor = doctor;
	}
	public DoctoresHome getCrud() {
		return crud;
	}
	public void setCrud(DoctoresHome crud) {
		this.crud = crud;
	}
	
	public void agregar(){
		crud.attachClean(doctor);
		RequestContext.getCurrentInstance().closeDialog(doctor);
		String tipo = (doctor.getSexo().equals("hombre"))?"Bienvenido Dr.: ":"Bienvenida Dra: ";
		msg=tipo+ doctor.getNombre() + " Fecha: "+ fecha();
		System.out.println(msg);
	}
	
	
	private void dialogMensaje(String titulo, String mensaje){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje );        
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	private String fecha(){
    	SimpleDateFormat formateador = new SimpleDateFormat(
        "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
    	Date fechaDate = new Date();
    	String fecha = formateador.format(fechaDate);
    	return (fecha);
    }
}
