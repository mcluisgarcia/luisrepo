package prueba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class People {

	private final Map<String, Person> largeCrowd;

	public People() {
		this.largeCrowd = createCrowd(1000);
	}
	
	public Map<String, Person> createCrowd(int i){
		
		Map<String, Person> createCrowd = new HashMap<String, Person>();
		
		
		Person p = new Person();
		p.setName("juan");
		p.setAge(22);
		p.setAddress("calle 1");
		createCrowd.put("1", p);
		
		p = new Person();
		p.setName("john");
		p.setAge(22);
		p.setAddress("calle 2");
		createCrowd.put("2", p);
		
		return createCrowd;
	}
	
	public List<Person> getMatchFromLargeCrowd(String filter){
		List<Person> replay = new ArrayList<Person>();
		Pattern regex = Pattern.compile(filter, Pattern.CASE_INSENSITIVE );
		for(Person p: largeCrowd.values()){
			if(regex.matcher(p.getName()).find() ){
				replay.add(p);
			}
		}
		return replay;
	}

}
