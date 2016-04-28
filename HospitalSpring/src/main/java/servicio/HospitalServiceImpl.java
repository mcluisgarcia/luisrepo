package servicio;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import modelo.DoctorDTO;
import modelo.DropDown;
import modelo.LoginDAO;
import modelo.LoginDTO;

public class HospitalServiceImpl implements HospitalService {

	private LoginDAO crud = new LoginDAO();
	public HospitalServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
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

	@Override
	public void cita() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registroDoctor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registroHorarios() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> getSemanas() {
		// TODO Auto-generated method stub
		Map referenceData = new HashMap();
    	Map<String,String> semanas = new LinkedHashMap<String,String>();
    	for(int i=0; i<53; i++){
			semanas.put(""+i, ""+i);
		}
    	referenceData.put("semanas", semanas);
		return referenceData;
	}

}
