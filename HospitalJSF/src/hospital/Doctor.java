package hospital;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Doctor {
	
	private Archivo archivo;
	private String nombre;
	private String edad;	
	private String email;
	private String password;
	private String sexo;
	private String identificacion;
	private String especialidad;
	
	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getRegistrar(){	
		System.out.println("creando"); 
		ArrayList<String> datos = new ArrayList<String>();
		datos.add("identificacion = "+identificacion);
		datos.add("edad = "+edad);
		datos.add("email = "+email);
		datos.add("especialidad = "+especialidad);
		datos.add("nombre = "+nombre);
		datos.add("sexo = "+sexo);
		datos.add("password = "+password);
		
		String file= System.getProperty("user.dir")+"\\Doctores.txt";		
		archivo = new Archivo(file);		 
		String nom = (recorreLista(datos))? "Bienvenido "+nombre: null;
		
		return nom;
	}
	
	private boolean recorreLista(ArrayList<String> datos){
		boolean registro = false;
		Iterator itr = datos.iterator();
		 try {
			archivo.saveInformacion("--------------", true);
		
        while(itr.hasNext()) {
           String dato = (String)itr.next();
           System.out.println(dato);          
           archivo.saveInformacion(dato+',', false);
           registro = true;
        }
        	archivo.saveInformacion(" ", true);
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
		 return registro;
	}
}
