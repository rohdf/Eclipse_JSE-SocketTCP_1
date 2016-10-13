package nsis.dico;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Dico extends HashMap<String, String> {
    private static final long serialVersionUID = 1L;
    
    private String fichierSource;

    public Dico(String chemin){
        fichierSource = chemin;
    }


    public void charger() throws IOException{
      
    	BufferedReader in = new BufferedReader(new FileReader(fichierSource));
    	String str;
    	String[] keyAndValue = new String[2];
    	String delim = "/";
    	while ((str=in.readLine()) != null) {
    	  StringTokenizer st = new StringTokenizer(str, delim);
    	  int i = 0;
    	  while (st.hasMoreTokens()) {
    	         keyAndValue[i++] = st.nextToken();
    	     }
    	  this.put(keyAndValue[0], keyAndValue[1]);
    	}
    	in.close();
     
    }


    public String consulter(String clef){

    	return this.get(clef);

    }



    public void creer(String clef,String valeur) throws IOException{

      this.put(clef, valeur);
      sauvegarde(clef, valeur);
    }

    public void listerStdout(){

     Iterator iterator = entrySet().iterator();
     while (iterator.hasNext()) {
      Map.Entry mapEntry = (Map.Entry) iterator.next();
      System.out.println("Clé: " + mapEntry.getKey()
       + ",Valeur :" + mapEntry.getValue());
     }

    }
    
    private void sauvegarde(String clef,String valeur){
    	PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(fichierSource,true));
			out.println(clef+"/"+valeur);
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
    }

}
