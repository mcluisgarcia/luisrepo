package servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelo.ComboboxM;
import modelo.DoctorDTO;
import modelo.LoginDAO;
import modelo.LoginDTO;

public class HospitalServiceImpl implements HospitalService {

	private LoginDAO crud = new LoginDAO();
	public HospitalServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public DoctorDTO login(LoginDTO login) {
		// TODO Auto-generated method stub
		String msg = "";
		DoctorDTO doc = crud.findLogin(login);
		System.out.println("crud "+doc);
		if (doc != null ) {
			
			return doc;

		} 
		return null;
	}

	
	public void doCita() {
		// TODO Auto-generated method stub

	}

	
	public List<ComboboxM> getSemanas() {
		// TODO Auto-generated method stub
		List<ComboboxM> lista = new ArrayList<ComboboxM>();
		for(int i = 1; i<=52 ; i++){
			ComboboxM combo = new ComboboxM();
			combo.setClave(i+"");
			combo.setValor(i+"");
			lista.add(combo);
		}
		
		return lista;
	}

}
