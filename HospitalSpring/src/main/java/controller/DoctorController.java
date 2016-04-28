package controller;

import java.io.Serializable;
import java.util.Date;




import modelo.DoctorDAO;
import modelo.DoctorDTO;



public class DoctorController implements Serializable{
	public DoctorDTO doctor = new DoctorDTO();
	
	private String msg = "";
	private DoctorDAO crud = new DoctorDAO();
	
	
	public DoctorDTO getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}
	public DoctorDAO getCrud() {
		return crud;
	}
	public void setCrud(DoctorDAO crud) {
		this.crud = crud;
	}
	
	public void agregar(){
		//crud.create(doctor);
		System.out.println(crud.msg);
		RequestContext.getCurrentInstance().closeDialog(doctor);
		msg="Bienvenido Dr. "+ doctor.getNombre() + " Fecha: "+ new Date().toString();
		//dialogMensaje("Listo!!", msg);
		System.out.println(msg);
	}
	
	public String getMsg(){
		System.out.println("mensaje: "+ msg);
		return msg;
	}
	
	private void dialogMensaje(String titulo, String mensaje){
		
	}
}
