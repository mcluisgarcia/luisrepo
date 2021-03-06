package hbm;

// Generated 19/04/2016 04:15:15 PM by Hibernate Tools 3.4.0.CR1

/**
 * Profesor generated by hbm2java
 */
public class Profesor implements java.io.Serializable {

	private int id;
	private Curso curso;
	private String nombre;

	public Profesor() {
	}

	public Profesor(int id) {
		this.id = id;
	}

	public Profesor(int id, Curso curso, String nombre) {
		this.id = id;
		this.curso = curso;
		this.nombre = nombre;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
