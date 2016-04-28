package vehiculo;

public class Vehicle {
	private String vehicleId;
	private String color;
	private int wheel;
	private int seat;

	

	
	public Vehicle(String vehicleId, String color, int wheel, int seat) {
		this.vehicleId = vehicleId;
		this.color = color;
		this.wheel = wheel;
		this.seat = seat;
	}


	public Vehicle() {
		// TODO Auto-generated constructor stub
	}


	

	public String getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	// Constructors, Getters and Setters
}
