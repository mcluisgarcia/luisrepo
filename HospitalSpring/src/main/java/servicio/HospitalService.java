package servicio;

import java.util.List;
import java.util.Map;

import modelo.DoctorDTO;
import modelo.DropDown;
import modelo.LoginDTO;

public interface HospitalService {

	DoctorDTO login(LoginDTO login);
	void cita();
	void registroDoctor();
	void registroHorarios();
	Map<String, String> getSemanas();

}
