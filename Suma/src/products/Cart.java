package products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="cart")
@SessionScoped
public class Cart implements Serializable{

	
		private List<String> productIds;
		
		
		public Cart() {
			productIds = new ArrayList<String>();
		}
		
		public void add(String pid) {
			productIds.add(pid);
		}

		public List<String> getProductIds() {
			return productIds;
		}

		public void setProductIds(List<String> productIds) {
			this.productIds = productIds;
		}

}
