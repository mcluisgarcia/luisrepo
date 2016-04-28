package dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class People {
	private final Map<String,Person> largeCrowd;
	
	public People(){
		this.largeCrowd= createCrowd(1000);
	}
	public Map<String,Person> createCrowd(int i){
		Map<String,Person> createCrowd=new HashMap<String,Person>();
		
		Person p=new Person();
		p.setName("Juan");
		p.setAge(22);
		p.setAddress("Calle 1");
		createCrowd.put("1",p);
		
		p=new Person();
		p.setName("John");
		p.setAge(27);
		p.setAddress("calle 2");
		createCrowd.put("2", p);
		
		return createCrowd;
	}
	public List<Person> getMatchingFromLargeCrowd(String filter){
		PersonasDao personasdao=new PersonasDao();
		return personasdao.read(filter);
	}
}
