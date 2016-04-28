package strutsActions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class UserRegistrationMultiAction extends DispatchAction{


	public ActionForward processPage1(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		System.out.println("utilizando ActionForward");
		return null;
	}
	
	public ActionForward processPage2(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		System.out.println("utilizando otro ActionForward");
		return null;
	}
	
	
}
