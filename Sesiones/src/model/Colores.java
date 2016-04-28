package model;

import java.util.ArrayList;
import java.util.List;

public class Colores {

	private String color;
	private static List<String> lista = new ArrayList();
	
	public Colores(String color) {
		// TODO Auto-generated constructor stub
		this.color = color;
		lista.add(color);
	}
	
	public String getColor(){
		return this.color;		
		
	}
	
	public List getColores(){
		return lista;
	}
	
	public void limpiaLista()
	{
		lista.clear();
	}
	
	public boolean isEmpty(){
		return lista.isEmpty();	
	}
}
