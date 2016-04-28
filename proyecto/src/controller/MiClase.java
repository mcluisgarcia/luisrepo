package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MiClase extends HttpServlet {

	public MiClase() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse response)throws IOException{
		//System.out.println("Hola");
		//response.getWriter().println("Hola");
		System.out.println("Arrancando JavaEE!!!!");
		
		String n = req.getParameter("n1");
		String n2 = req.getParameter("n2");
		
	}
}
