package service;

import java.util.ArrayList;

public interface DAO {
	void create(Object registro);
	void update(Object registro);
	void delete(Object id);
	
	Object findById(Object id);
	ArrayList findAll();
}
