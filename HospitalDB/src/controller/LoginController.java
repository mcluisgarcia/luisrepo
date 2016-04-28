package controller;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import modelo.DoctorDAO;
import modelo.DoctorDTO;
import modelo.LoginDAO;
import modelo.LoginDTO;

import org.primefaces.context.RequestContext;

@ManagedBean(name="login")
@SessionScoped
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
		String nombre = crud.findLogin(login);
		if(nombre != "" || nombre != null){
			msg = "Bienvenido Dr(a) "+ nombre;
			//contexto.addCallbackParam("view", "doctores.xhtml");
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
}
