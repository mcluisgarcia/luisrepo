package hospital;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="login")
@RequestScoped
public class Login {
	
	private String email;
	private String password;
	private Archivo archivo;
	private ArrayList<String> listaRegistros;

	public void pastLogin(String email, String password) throws UsuarioNoExiste{
		// TODO Auto-generated constructor stub
		System.out.println("logeando");
		this.email = email;
		this.password = password;
		String file= System.getProperty("user.dir")+"\\Doctores.txt";
		conectarArchivo(file);
		//if(!buscarRegistro(email, password))
			//throw new UsuarioNoExiste();
	}
	
	public String getLogin() {
		if(email==null && password==null )
			return null;
		// TODO Auto-generated constructor stub		
		String file= System.getProperty("user.dir")+"\\Doctores.txt";
		conectarArchivo(file);
		String res = "Bienvenido "+buscarRegistro(email, password);
		if(res == null)
			res="Usuario no existe";//throw new UsuarioNoExiste();
		return res;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	private String buscarRegistro(String email, String password){
		int i = 0;
		Iterator<String> itr = listaRegistros.iterator();
          while(itr.hasNext()) {
        	  
        	  String reg= itr.next().trim();
        	  System.out.println(reg + reg.contains(password) + password);
        	  if(reg.contains(password) && reg.contains(email)){
        		  return reg.substring(reg.indexOf("=")+1, reg.indexOf(","));        		  
        	  }        	  
        	  	  		             
         }
          return null;
	}
	
	private void conectarArchivo(String dir){	
		listaRegistros = new ArrayList<String>();
		
		archivo = new Archivo(dir);
		try {
			listaRegistros = archivo.leerInformacion();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
