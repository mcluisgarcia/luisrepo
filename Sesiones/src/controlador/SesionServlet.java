package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Colores;

public class SesionServlet extends HttpServlet {

	private Colores color;
	private HttpSession sesion;
	
	public SesionServlet() {
		// TODO Auto-generated constructor stub
		//color = new Colores("negro");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse response)throws IOException, ServletException{
		//System.out.println("Hola");
		
		String colorF = req.getParameter("colorF");
		String salir= req.getParameter("limpia");
		
		sesion = req.getSession();
		req.setAttribute("sesionId", "");
		System.out.println(sesion);
		System.out.println(color);
		
		if(salir != null && color!=null) {
			sesion.invalidate();
			color.limpiaLista();
			req.setAttribute("sesionId", "Sesion finalizada");
			req.getRequestDispatcher("/index.jsp").forward(req, response);
		}
		else{
			
			if(colorF!=null){
				color = new Colores(colorF);
				sesion.setAttribute("color", color.getColores());
				
				req.setAttribute("sesionId", sesion.getId());
				//req.setAttribute("Colores", sesion.getServletContext().getAttribute("color"));
				req.setAttribute("ColoresF", sesion.getAttribute("color"));
				req.getRequestDispatcher("/index.jsp").forward(req, response);
			}
		}
		
		
		//System.out.println("Arrancando JavaEE!!!!");
	}

}
