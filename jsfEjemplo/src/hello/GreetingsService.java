package hello;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="foo")
@RequestScoped
public class GreetingsService {
	
	private String nombre;

	public GreetingsService(String nombre) {
		
		this.nombre = nombre;
		// TODO Auto-generated constructor stub
	}
	
	public String getSubject(){
		
		return "Luis";
	}

}
