package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Proceso {
	@WebMethod
	public String sayHello(){
		return "Hola ";
	}

}
