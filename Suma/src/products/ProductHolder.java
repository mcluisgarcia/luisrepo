package products;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "ph")
@RequestScoped
public class ProductHolder {
	
	@ManagedProperty(value = "#{cart}")
	private Cart cart;
	
	@ManagedProperty(value = "#{catalog}")
	private Catalog catalog;
	
	private Product currentProduct;
	
	public Product getCurrentProduct() {
		return currentProduct;
	}
	
	public void setCurrentProduct(Product currentProduct) {
		this.currentProduct = currentProduct;
	}
	
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public String addToCart() {
		System.out.println("Adding "+currentProduct.getId());
		cart.add(currentProduct.getId());
		return "added";
	}
	
	public String getProductId() {
		return currentProduct != null ? currentProduct.getId() : null;
	}
	
	public void setProductId(String pid) {
		currentProduct = catalog.getProduct(pid);
	}
	
	
}
