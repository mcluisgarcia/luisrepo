package hbm;

// Generated 19/04/2016 04:15:15 PM by Hibernate Tools 3.4.0.CR1

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios implements java.io.Serializable {

	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String phone;
	private String fax;

	public Usuarios() {
	}

	public Usuarios(String email) {
		this.email = email;
	}

	public Usuarios(String email, String firstName, String lastName,
			String password, String phone, String fax) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phone = phone;
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
