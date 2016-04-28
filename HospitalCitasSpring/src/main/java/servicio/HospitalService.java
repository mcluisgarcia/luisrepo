package servicio;

import java.util.List;

import modelo.DoctorDTO;
import modelo.LoginDTO;
import modelo.ComboboxM;

public interface HospitalService {

	DoctorDTO login(LoginDTO login);
	void doCita();
	List<ComboboxM> getSemanas();
}
