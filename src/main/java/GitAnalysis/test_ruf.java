package GitAnalysis;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class test_ruf {
	
	public static void main (String args[]){
		ArrayList <String> al_strtokens= new ArrayList <String> (); 
		al_strtokens = null; 
		StringTokenizing (al_strtokens, "MAIL-377 "); 
	
	
	}
	
	public static void StringTokenizing (ArrayList <String> al_strtokens, String tmpstr_line){
		StringTokenizer st = new StringTokenizer(tmpstr_line);
		System.out.println(tmpstr_line);
	    while (st.hasMoreTokens()) {
	    	al_strtokens.add(st.nextToken()); 
	    	System.out.println(al_strtokens.get(al_strtokens.size()-1));
	    }
	}
	
	
	

}
