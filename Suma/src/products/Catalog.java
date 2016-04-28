package products;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

import java.util.*;


@ManagedBean(name="catalog")
@ApplicationScoped

public class Catalog {
	private List<Product> products;

	public Catalog() {
		products = new ArrayList<Product>();
		products.add(new Product("p1", "Pencil", 1.20));
		products.add(new Product("p2", "Eraser", 2.00));
		products.add(new Product("p3", "Ball pen", 3.50));
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public Product getProduct(String pid) {
		for (Product p : products) {
			if (p.getId().equals(pid)) {
				return p;
			}
		}
		return null;
	}
}