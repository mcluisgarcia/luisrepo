package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Login {
	
	String email;
	String password;
	Archivo archivo;
	ArrayList<String> listaRegistros;

	public Login(String email, String password) throws UsuarioNoExiste{
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
		String file= System.getProperty("user.dir")+"\\Doctores.txt";
		conectarArchivo(file);
		if(!buscarRegistro(email, password))
			throw new UsuarioNoExiste();
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
	
	
	private boolean buscarRegistro(String email, String password){
		int i = 0;
		Iterator<String> itr = listaRegistros.iterator();
          while(itr.hasNext()) {
        	  
        	  String reg= itr.next().trim();
        	  System.out.println(reg + reg.contains(password) + password);
        	  if(reg.contains(password) && reg.contains(email)){
        		  return true;        		  
        	  }        	  
        	  	  		             
         }
          return false;
	}
	
	private void conectarArchivo(String dir){	
		listaRegistros = new ArrayList<String>();
		//System.getProperty("user.dir")+File.separatorChar+
		//dir= dir.substring(0,dir.indexOf(".")) +"\\"+ dir.substring(dir.lastIndexOf("\\")+1) + "\\src\\model\\Doctores.txt";//getClass().getResource("Doctores.txt").getPath();
		//String dir = Login.class.getResource("/model/Doctores.txt").getPath();//Paths.get(".").toAbsolutePath().normalize().toString();
		//System.out.println("leyendo de : "+dir);
		archivo = new Archivo(dir);//new Archivo("C:/Users/TR 06 EM/Desktop/luisHG/workspace/Hospital/src/model/Doctores.txt");
		try {
			listaRegistros = archivo.leerInformacion();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
