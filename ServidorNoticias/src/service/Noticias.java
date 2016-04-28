package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

import service.NewsDAO;
import service.NewsDTO;

@WebService
public class Noticias {

	@WebMethod
	public NewsDTO generarNoticias(){
		NewsDAO crud = new NewsDAO();
		 Random rnd = new Random();
		 int id = (int)rnd.nextDouble() * 4 + 1;
		NewsDTO noticia = (NewsDTO)crud.findById(""+id);
		
		
		return noticia;
	}

}
