package vehiculo;

public interface VehicleDAO {
	public void insert(Vehicle vehicle);

	public void update(Vehicle vehicle);

	public void delete(Vehicle vehicle);

	public Vehicle findByVehicleNo(String vehicleNo);
}
