package stock;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="b1")
@RequestScoped

public class QuoteRequest {

	
	
	private String sym = "MSFT";
	public QuoteRequest() {
		System.out.println("Creating b1");
	}
	
	public String getSym() {
		System.out.println("getting sym");
	return sym;
	}
	
	public void setSym(String sym) {
		System.out.println("setting sym to: " + sym);
		this.sym = sym;
	}
	
	public int getStockValue(){
		return Math.abs(sym.hashCode());
	
	}

}
