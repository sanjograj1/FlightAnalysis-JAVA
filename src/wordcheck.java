//required libraries
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashSet;
import java.util.Set;

public class wordcheck {
	
	//Function to calculate minimum distance
	public static int minDistance(String firstcharacter, String secondcharacter) {
		
		//assigning length of both words to variables
        int m = firstcharacter.length();
        int n = secondcharacter.length();
        
        //assigning both lengths to an array
        int[][] editDistance = new int[m + 1][n + 1];
        
        //Dynamic Programming algorithm
        for(int i = 0; i <= m; i++)
        	editDistance[i][0] = i;
        for(int i = 1; i <= n; i++)
        	editDistance[0][i] = i;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(firstcharacter.charAt(i) == secondcharacter.charAt(j))
                	editDistance[i + 1][j + 1] = editDistance[i][j];
                else {
                    int a = editDistance[i][j];
                    int b = editDistance[i][j + 1];
                    int c = editDistance[i + 1][j];
                    editDistance[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    editDistance[i + 1][j + 1]++;
                }
            }
        }
        return editDistance[m][n];
    }
	
	//Function to parse HTML file and store in an Array
	static Set<String> htmlParse (){
		
		//fetching document
		String url = "http://www.citymayors.com/gratis/canadian_cities.html";

		
	    Document docs;
	    
	    //adding try block to check for exceptions
		try {
			docs = Jsoup.connect(url).get();
		    String body = docs.body().text();
		    
		    //adding words in an array
		    String[] string =  body.split("\\s+");
		    Set<String> name = new HashSet<>();
		    for (int i = 0; i < string.length; i++) {
		        //deleting non alphanumeric characters
		        name.add(string[i].replaceAll("[^\\w]", "").toLowerCase());
		    }
		   
		    //System.out.println("Length of string is: "+ str.length);
		    
		    
		    return name;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	//Main Function
	
}
