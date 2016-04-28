package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Suma;

public class SumaServlet extends HttpServlet {

	public SumaServlet() {
		// TODO Auto-generated constructor stub
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse response)throws IOException, ServletException{
		//System.out.println("Hola");
		//response.getWriter().println("Hola");
		System.out.println("Arrancando JavaEE!!!!");
		
		String n = req.getParameter("n1");
		String n2 = req.getParameter("n2");
		Suma suma = new Suma();
		int res = suma.operacion(Integer.parseInt(n), Integer.parseInt(n2));
		req.setAttribute("resultado",res);
		req.getRequestDispatcher("/resultado.jsp").forward(req, response);//forward
		
	}

}
