package operaciones;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="suma")
@RequestScoped

public class Suma {

	int a;
	int b;
	int total;
	public Suma() {
		// TODO Auto-generated constructor stub
	}
	

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	public void setA(int a) {
		this.a = a;
	}

	public int getTotal() {
		return a+b;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getA() {
		return a;
	}
	
	

}
