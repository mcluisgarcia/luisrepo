package products;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;



public class OnDetailActionListener implements ActionListener {
	@Override
	public void processAction(ActionEvent ev) throws AbortProcessingException {
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		Product p = (Product) app.evaluateExpressionGet(context, "#{p}",
				Product.class);
		ProductHolder ph = (ProductHolder) app.evaluateExpressionGet(
				context,
				"#{ph}",
				ProductHolder.class);
				ph.setCurrentProduct(p);
	}
	
	

}
