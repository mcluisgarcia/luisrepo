package model;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Doctor {
	
	private Archivo archivo;
	
	public Doctor(ArrayList<String> datos ){		
		String file= System.getProperty("user.dir")+"\\Doctores.txt";//dir2.substring(0,dir2.indexOf(".")) + dir2.substring(dir2.lastIndexOf("\\")+1) + "\\src\\model\\Doctores.txt";//getClass().getResource("Doctores.txt").getPath();
		//System.out.println("Guardando en " + file  );
		archivo = new Archivo(file);		 
		recorreLista(datos); 
		//archivo.saveInformacion("", true);
	}
	
	private void recorreLista(ArrayList<String> datos){
		Iterator itr = datos.iterator();
		 archivo.saveInformacion("--------------", true);	
        while(itr.hasNext()) {
           String dato = (String)itr.next();
           System.out.println(dato);          
           archivo.saveInformacion(dato+',', false);		
        }
        archivo.saveInformacion(" ", true);
	}
}
