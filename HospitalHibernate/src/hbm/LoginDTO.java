package hbm;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileSystem;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class LoginDTO implements Serializable {
	
	private String email;
	private String password;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
