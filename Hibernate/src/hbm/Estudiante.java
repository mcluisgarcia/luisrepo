package hbm;

// Generated 19/04/2016 04:15:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Estudiante generated by hbm2java
 */
public class Estudiante implements java.io.Serializable {

	private int id;
	private String nombre;
	private Set cursos = new HashSet(0);

	public Estudiante() {
	}

	public Estudiante(int id) {
		this.id = id;
	}

	public Estudiante(int id, String nombre, Set cursos) {
		this.id = id;
		this.nombre = nombre;
		this.cursos = cursos;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getCursos() {
		return this.cursos;
	}

	public void setCursos(Set cursos) {
		this.cursos = cursos;
	}

}
