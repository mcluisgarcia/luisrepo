package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import hbm.Doctores;
import hbm.LoginDAO;
import hbm.LoginDTO;

import org.primefaces.context.RequestContext;

public class LoginController {

    private LoginDTO login = new LoginDTO();	
	private String msg = "";
	private LoginDAO crud = new LoginDAO();
	

	
   public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}

public void doLogin(){
		Doctores doc = crud.findLogin(login);
		if(doc != null){
			String tipo = (doc.getSexo().equals("hombre"))?"Bienvenido Dr.: ":"Bienvenida Dra: ";
			msg = tipo + doc.getNombre() + " Fecha: "+convFechaHoy();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ObjDoc", doc);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bienvenida", msg);
			ExternalContext ectx =FacesContext.getCurrentInstance().getExternalContext();
			try {
				ectx.redirect(ectx.getRequestContextPath() + "/faces/doctores.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			dialogMensaje("Usuario No existe","Password o Usuario incorrecto");
		System.out.println(msg);
	}
	
	public String getMsg(){
		System.out.println("mensaje: "+ msg);
		return msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	private void dialogMensaje(String titulo, String mensaje){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje );
		FacesContext.getCurrentInstance().addMessage(null, message);
        
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	private String convFechaHoy(){
    	SimpleDateFormat formateador = new SimpleDateFormat(
	   "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
	   Date fechaDate = new Date();
	   String fecha = formateador.format(fechaDate);
	   return fecha;
    }
}
