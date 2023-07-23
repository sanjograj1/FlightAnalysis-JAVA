import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class main {
	private static Scanner sc;
	private static WebDriver wd;
	public static void main(String[] args) throws Exception {
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		wd = new ChromeDriver(ops);

		// Start data collection
		System.out.println("Flight Tracker");
		Scanner var1 = null;
		Scanner var2 = null;

		try {
			var1 = new Scanner(System.in);
			var2 = new Scanner(System.in);

			File ff = new File("./src/famous_count.txt");
			if (ff.exists()) {
				List<String> w = new ArrayList<String>();
				Scanner var_read = new Scanner(ff);
				while (var_read.hasNextLine()) {
					w.add(var_read.nextLine());
				}
				var_read.close();
				Map<String, Integer> var_arg2_frequency = new HashMap<>();
				for (String arg2 : w) {
					Integer arg1 = var_arg2_frequency.get(arg2);
					if (arg1 == null)
						var_arg2_frequency.put(arg2, 1);
					else {
						var_arg2_frequency.put(arg2, arg1 + 1);
					}
				}
				System.out.println("####");
				System.out.println("Famous places to visit:");
				for (String key : var_arg2_frequency.keySet()) {
					System.out.println(key.toUpperCase() + " " + var_arg2_frequency.get(key) + " times");
				}
				System.out.println("####");
			} else {
				ff.createNewFile();
			}

			Map<String, String> cc = new HashMap<String, String>();
			cc.put("Delhi", "NCR");
			cc.put("Bihar", "NCA");
			cc.put("Haryana", "NCR");
			cc.put("Mumbai", "NCM");

			System.out.println(
					"Please choose source and destination: \nDelhi \nBihar \nHaryana \nMumbai ");
			System.out.println("###");
			System.out.println("Enter name of source");
			String src = var1.nextLine();

			while (!src.matches("[a-zA-Z_]+")) {
				System.out.println("Invalid param2");
				System.out.println("Enter name of source");
				src = var2.nextLine();
			}

			src = src.toLowerCase();
			String filearg2 = src + "\n";
			String param2 = cc.get(src);
			if (param2 == null) {
				param2 = arg2Check(src);
				filearg2 = param2 + "\n";
				System.out.println(filearg2);
				param2 = cc.get(param2);
			}

			Files.write(Paths.get("src/famous_counts.txt"), filearg2.getBytes(), StandardOpenOption.APPEND);

			System.out.println("-----------");
			System.out.println("Enter name of destination");

			String var_destination = var2.nextLine();
			while (!var_destination.matches("[a-zA-Z_]+")) {
				System.out.println("Wrong destination");
				System.out.println("Enter name of destination");
				var_destination = var1.nextLine();
			}

			var_destination = var_destination.toLowerCase();
			filearg2 = var_destination + "\n";
			String var_destination1 = cc.get(var_destination);

			if (var_destination1 == null) {
				var_destination1 = arg2Check(var_destination1);
				filearg2 = var_destination1 + "\n";
				var_destination1 = cc.get(var_destination1);
			}
			Files.write(Paths.get("src/famous_counts.txt"), filearg2.getBytes(), StandardOpenOption.APPEND);

			System.out.println("###");
			System.out.println("Departure Date? : yymmdd");
			String leave_date = var1.nextLine();

			System.out.println("###");
			System.out.println("Total Number of Travellers?");
			String var_counts = var2.nextLine();

			String var_url = "https://www.skyscanner.ca/transport/flights/" + param2 + "/" + var_destination1 + "/" + leave_date
					+ "/?adultsv2=" + var_counts
					+ "&cabinclass=economy&children=0&childrenv2=&destinationentityid=27537411&inboundaltsenabled=false&infants=0&originentityid=27536640&outboundaltsenabled=false&preferdirects=false&ref=home&rtn=0";

			List<String> array_url = new ArrayList<String>();
			array_url.add(var_url);
			crawlUrls(2, array_url, new ArrayList<String>());

        }}}

			//distance function
		public static int finding_minimum_dis(String f_word, String s_word) {

		
			int params3 = f_word.length();
			int params4 = s_word.length();
	
			
			int[][] distance = new int[params3 + 1][params4 + 1];
	
		
			for (int i = 0; i <= params3; i++)
				distance[i][0] = i;
			for (int i = 1; i <= params4; i++)
				distance[0][i] = i;
	
			for (int i = 0; i < params3; i++) {
				for (int j = 0; j < params4; j++) {
					if (f_word.charAt(i) == s_word.charAt(j))
						distance[i + 1][j + 1] = distance[i][j];
					else {
						int variable1 = distance[i][j];
						int variable2 = distance[i][j + 1];
						int variable3 = distance[i + 1][j];
						distance[i + 1][j + 1] = variable1 < variable2 ? (variable1 < variable3 ? variable1 : variable3) : (variable2 < variable3 ? variable2 : variable3);
						distance[i + 1][j + 1]++;
					}
				}
			}
			return distance[params3][params4];
		}