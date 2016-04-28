package hospital;

import java.io.*;
import java.util.*;


public class Archivo
{
   
    private String dato;
    private String archivo;
    private ArrayList<String> lista = new ArrayList<String>();
   
    
    public Archivo(String archivo){
       this.archivo = archivo;     
       
    }
    
        
    public ArrayList<String> leerInformacion() throws FileNotFoundException, IOException{
            
      String info;
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
      while((info = b.readLine( ))!=null) {         
          lista.add(info.replace(" ",""));
      }
      
      b.close();
      
      return lista;
    }   
    
    
    public void buscaYactualizaInfo(String texto) throws FileNotFoundException, IOException {
      
      String info;
      String textoArchivo="";
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
      while((info = b.readLine( ))!=null) {  
         String[] temp = info.split("=");
         String[] ingr = texto.split("=");
                  
         if ( temp[0].trim().equals(ingr[0].trim()) ){                
                info=info.replace(info,texto);                
         }            
         textoArchivo+=info+"\r\n";            
      }
      
      FileWriter fw = new FileWriter(archivo);
      fw.write(textoArchivo.replaceAll("(?m)^[ \t]*\r?\n", ""));
      fw.close();
      
      b.close();
    }
    
    private String[] rellenarArreglo(ArrayList<String> lista){
        String datos[] = new String[lista.size()];
        datos = lista.toArray(datos);
       
        return datos;
    }
    
    public void saveInformacion(String dato, boolean salto) throws IOException{
            		   		
    		File file =new File(archivo);    		
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        bufferWritter.write(dato);  
	        if(salto) bufferWritter.newLine(); 
	        bufferWritter.close(); 
	        
    	
    }
}
