package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Doctor;
import model.Login;
import model.UsuarioNoExiste;


public class Controlador extends HttpServlet {

	public Controlador() {
		// TODO Auto-generated constructor stub
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse response)throws IOException, ServletException{
		//System.out.println("Hola");
		//response.getWriter().println("Hola");
		
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String registro = req.getParameter("add");		
		//System.out.println(registro+email + password);
		//ArrayList<String> listaprueba = new ArrayList<String>();
				
		//listaprueba.add("nombreservlet = luisservlet");
		//new Doctor(listaprueba);
		if( registro!=null && registro.equals("Agregar") ){
			ArrayList<String> registroDr = new ArrayList<String>();
			registroDr.add("nombre = "+req.getParameter("nombreD"));
			registroDr.add("email = "+req.getParameter("emailD"));
			registroDr.add("password = "+req.getParameter("passwordD"));
			registroDr.add("edad = "+req.getParameter("edad"));
			registroDr.add("ife = "+req.getParameter("ife"));
			registroDr.add("especialidad = "+req.getParameter("especialidad"));
			registroDr.add("sexo = "+req.getParameter("sexo"));
			new Doctor(registroDr);
			req.setAttribute("resultado","Estas registrado corectamente");
			req.getRequestDispatcher("/doctores.jsp").forward(req, response);//forward
		}
		if( email!=null&& password!=null ){
			Login login = null;
			try {
				login = new Login(email,password);
				req.setAttribute("resultado","Bienvenido " + login.getEmail());				
				
			} catch (UsuarioNoExiste e) {
				// TODO Auto-generated catch block
				req.setAttribute("resultado","Password incorrecto o Usuario " + email + " no existe");
				//e.printStackTrace();
			}
			
			req.getRequestDispatcher("/doctores.jsp").forward(req, response);//forward
		}
		
	}

}
