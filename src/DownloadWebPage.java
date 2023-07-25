import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class DownloadWebPage {
  
    public DownloadWebPage(String Webportal,String DocumentName)
    {
        try {
            // Define the URL object
            URL url = new URL(Webportal);
            
            // Create a BufferedReader object to read data from the URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            
            // Create a BufferedWriter object to write data to a file
            BufferedWriter writer = new BufferedWriter(new FileWriter("./resources/WebPages/"+DocumentName+".html"));
            
            String lines;
            
            // While there is data to read from the URL, write it to the file
            while ((lines = reader.readLine()) != null) {
                writer.write(lines);
            }
            
            // Close the BufferedReader and BufferedWriter objects
            reader.close();
            writer.close();
        }
        // Catch any MalformedURLExceptions
        catch (MalformedURLException mue) {
            System.out.println("Exception raised");
        }
        // Catch any IOExceptions
        catch (IOException ie) {
        }
        // Catch any other exceptions
        catch(Exception e) {}
    }
}
