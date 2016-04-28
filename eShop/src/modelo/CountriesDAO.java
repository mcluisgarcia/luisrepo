package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import prueba.Person;

public class CountriesDAO{
	
	private Connection conexion = Conexion.getConexion();
	private Map<String, Countries> countries;
	
	public CountriesDAO(){
		findAll(); 
	}
	
	private void findAll() {
		 countries = new HashMap<String, Countries>();

		try {
			PreparedStatement s = conexion.prepareStatement("select * from countries");
			ResultSet res = s.executeQuery();
			int i = 0;
			while(res.next()){
				Countries pais = new Countries();
				pais.setCountryId(res.getString("COUNTRY_ID"));
				pais.setCountryName(res.getString("COUNTRY_NAME"));
				pais.setRegionId(res.getString("REGION_ID"));
				countries.put(""+i, pais);
				i++;
			}
			//return countries;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return null;
	}
	
	public List<Countries> getMatchFromLargeCrowd(String filter){
		
		List<Countries> replay = new ArrayList<Countries>();
		Pattern regex = Pattern.compile(filter, Pattern.CASE_INSENSITIVE );
		for(Countries p: countries.values()){
			if(regex.matcher(p.getCountryName()).find() ){
				replay.add(p);
			}
		}
		return replay;
	}

	

}
