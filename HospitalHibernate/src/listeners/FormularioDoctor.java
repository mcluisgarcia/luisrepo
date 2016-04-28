package listeners;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import hbm.Doctores;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name="formDoc")
//@RequestScoped
@SessionScoped
public class FormularioDoctor {

	private FacesMessage message;
	private Doctores doc;
	
	
	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public void registrarDoc() {
		System.out.println("formulario");
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("registroDoc", options, null);
    }
     
    public void onClickRegistra(SelectEvent event) throws IOException {
    	System.out.println("registro");    	
        doc = (Doctores) event.getObject(); 
        System.out.print(doc.getNombre());
        String tipo = (doc.getSexo().equals("hombre"))?"Bienvenido Dr.: ":"Bienvenida Dra: ";
        String msg =  tipo + doc.getNombre() + " \n "+ fecha();
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto ", msg );
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bienvenida", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        
        RequestContext.getCurrentInstance().execute("setTimeout(function(){"
        		//+ "PrimeFaces.dialog.DialogHandler.messageDialog.hide()}"
        		+ "  window.location = 'doctores.xhtml'}"
        		+ ",3000)");
        //redirige();
    }
    
    public void redirige() throws IOException{
    	
    	 ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
         
       //  try {
         	
 		//	Thread.sleep(3000);
 			ctx.redirect( ctx.getRequestContextPath() + "/faces/doctores.xhtml" );
 		//} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			//e.printStackTrace();
 		//}
         
    }
    
    public String getMensaje(){
    	
    	if(message == null)
    		return null;
    	System.out.println("formDoc " + message.getDetail());
    	//ExternalContext tmpEC;
        //Map sMap;
        //tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        //sMap = tmpEC.getSessionMap();
        //FacesMessage msg = (FacesMessage) sMap.get("message");
        //System.out.println(msg.getDetail());
        
    	return message.getDetail();
    }
    
    private String fecha(){
    	SimpleDateFormat formateador = new SimpleDateFormat(
        "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
    	Date fechaDate = new Date();
    	String fecha = formateador.format(fechaDate);
    	return (fecha);
    }
}
