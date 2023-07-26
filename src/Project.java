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

public class Project {
	private static Scanner sc;

	private static WebDriver parameter2;

	public static void main(String[] parameter1) throws Exception {
	    // Initialize ChromeOptions to configure the ChromeDriver
	    ChromeOptions ops = new ChromeOptions();
	    ops.addArguments("--remote-allow-origins=*");
	    parameter2 = new ChromeDriver(ops);

	    // Start data collection
	    System.out.println("Flight Tracker");
	    Scanner variable1 = null;
	    Scanner variable2 = null;

	    try {
	        variable1 = new Scanner(System.in);
	        variable2 = new Scanner(System.in);

	        // Check if the search_frequency.txt file exists
	        File freq_var = new File("./src/search_frequency.txt");
	        if (freq_var.exists()) {
	            // Read the contents of the file into a list
	            List<String> word_var = new ArrayList<String>();
	            Scanner read_var = new Scanner(freq_var);
	            while (read_var.hasNextLine()) {
	                word_var.add(read_var.nextLine());
	            }
	            read_var.close();
	            Map<String, Integer> wordfreq = new HashMap<>();
	            for (String word : word_var) {
	                Integer integer = wordfreq.get(word);
	                if (integer == null)
	                    wordfreq.put(word, 1);
	                else {
	                    wordfreq.put(word, integer + 1);
	                }
	            }

	            // Print the word frequency for famous places
	            System.out.println("Famous Places?");
	            for (String key : wordfreq.keySet()) {
	                System.out.println(key.toUpperCase() + " " + wordfreq.get(key) + " Times");
	            }
	        } else {
	            // If the file doesn't exist, create it
	            freq_var.createNewFile();
	        }

	        Map<String, String> code = new HashMap<String, String>();
	        code.put("windsor", "yqg");
	        code.put("toronto", "ytoa");
	        code.put("montreal", "yul");
	        code.put("vancouver", "yvr");

	        System.out.println(
	                "Please choose source and destination from the following: \nWindsor \nToronto \nMontreal \nVancouver ");
	        System.out.println("-----------");
	        System.out.println("Source Name?");
	        String src = variable1.nextLine();

	        while (!src.matches("[a-zA-Z_]+")) {
	            System.out.println("Invalid var_source");
	            System.out.println("Enter name of var_source");
	            src = variable2.nextLine();
	        }

	        src = src.toLowerCase();
	        String variable3 = src + "\n";
	        String var_source = code.get(src);
	        if (var_source == null) {
	            var_source = wordCheck(src);
	            variable3 = var_source + "\n";
	            System.out.println(variable3);
	            var_source = code.get(var_source);
	        }

	        Files.write(Paths.get("src/search_frequency.txt"), variable3.getBytes(), StandardOpenOption.APPEND);

	        System.out.println("Destination name?");
	        String dest = variable2.nextLine();
	        while (!dest.matches("[a-zA-Z_]+")) {
	            System.out.println("Wrong var_des");
	            System.out.println("Destination Name");
	            dest = variable1.nextLine();
	        }

	        dest = dest.toLowerCase();
	        variable3 = dest + "\n";
	        String var_des = code.get(dest);

	        if (var_des == null) {
	            var_des = wordCheck(dest);
	            variable3 = var_des + "\n";
	            var_des = code.get(var_des);
	        }
	        Files.write(Paths.get("src/search_frequency.txt"), variable3.getBytes(), StandardOpenOption.APPEND);
	        System.out.println("Departure Date: yymmdd");
	        String leavedate = variable1.nextLine();

	        System.out.println("Total passengers");
	        String var_count = variable2.nextLine();

	        String url = "https://www.skyscanner.ca/transport/flights/" + var_source + "/" + var_des + "/" + leavedate
	                + "/?adultsv2=" + var_count
	                + "&cabinclass=economy&children=0&childrenv2=&var_desentityid=27537411&inboundaltsenabled=false&infants=0&originentityid=27536640&outboundaltsenabled=false&preferdirects=false&ref=home&rtn=0";

	        List<String> var_u = new ArrayList<String>();
	        var_u.add(url);
	        crawlUrls(2, var_u, new ArrayList<String>());

	        // End data collection
//variable2
	        while (true) {
				boolean exit = false;
				System.out.println("\n***********************");
				System.out.println("Choose from the menu and enter the number corresponding to each menu");
				System.out.println("1 - Inverted Index");
				System.out.println("2 - Frequency Count");
				System.out.println("3 - Page Ranking");
				System.out.println("4 - Word Completion");
				System.out.println("5 - Exit");
				System.out.println("\nEnter your choice Number");
				int n;
				try {
					n = variable2.nextInt();
					if (n > 0 && n < 6) {
						switch (n) {
							case 1:
								System.out.println("\n\n======================== Inverted Index ========================\n\n");

								for (File file : new File("src/resc/Web Pages/").listFiles()) {
									if (file.isFile() && file.getName().endsWith(".txt")) {
										String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
										if (content != null) {
											List<String> ret = InvertedIndex.parseContent(content);
											InvertedIndex.constructTrie(file.getName(), ret);
										}
									}
								}
								break;
							case 2:
								System.out.println("\n\n======================== Frequency Count ========================\n\n");

								for (File file : new File("src/resc/Web Pages/").listFiles()) {
									if (file.isFile() && file.getName().endsWith(".txt")) {
										String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
										if (content != null) {
											System.out.println("\n"+file.getName());
											String[] strArr = FrequencyCount
													.parseContent(content);
											FrequencyCount.printWordFrequency(strArr);
										}
									}
								}
								break;
							case 3:
								System.out.println("\n\n======================== Page Ranking ========================\n\n");
								System.out.print("Enter word: ");
								sc = new Scanner(System.in);
								String input = sc.nextLine();
								PageRanking pg = new PageRanking();

								Hashtable<String, Integer> pageRank = pg.matchPattern(input);

								if (pageRank.size() == 0)
									System.out.println("Not found");
								else {
									int totalOccurences = 0;
									for (int occurences : pageRank.values())
										totalOccurences += occurences;
									System.out.println("About " + totalOccurences + " matches ");
									System.out.println("Matches found in " + pageRank.size() + " web pages.\n");
									System.out.println("Matches\t Top 10 Pages");
									Map<String, Integer> sortedByValueDesc = pageRank.entrySet()
											.stream().limit(10)
											.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
											.collect(
													Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
									sortedByValueDesc.forEach((key, value) -> System.out.println("  " + value + " -- " + key));
								}
								break;
							case 4:
								System.out.println("\n\n======================== Word Completion ========================\n\n");
								WordCompletion.run();
								break;
							default:
								exit = true;
						}
						if (exit)
							break;

					} else
						System.out.println("Oops! Entered Wrong number");

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			variable1.close();
			variable2.close();
		}
	}
	private static void crawlUrls(int level, List<String> var_u, ArrayList<String> visited) throws Exception {
	    // TODO Auto-generated method stub
	    // Loop through each URL in the var_u list and crawl it at the specified level
	    for (String url : var_u) {
	        crawl(level, url, visited);
	    }
	    // Quit the ChromeDriver after crawling all URLs in the list
	    parameter2.quit();
	}

	private static void crawl(int level, String targetLink, ArrayList<String> visited) throws Exception {
	    // Print the targetLink to indicate the current URL being crawled
	    System.out.println(targetLink);

	    // Check if the current crawl level is within the limit (up to 3)
	    if (level <= 3) {
	        // Request the targetLink URL and retrieve the document
	        Document doc = request(targetLink, visited);

	        // If the document is not null and has a title, write it to a file in the "Web Pages" folder
	        if (doc != null && doc.title() != "") {
	            System.out.println("Scanning... " + doc.title());
	            BufferedWriter writer = new BufferedWriter(
	                    new FileWriter("./src/resc/Web Pages/" + doc.title() + ".txt"));
	            writer.write(doc.text().toLowerCase());
	            writer.close();
	        }

	        // If the document is not null, extract all the links and crawl them recursively
	        if (doc != null) {
	            for (Element link : doc.select("a[href]")) {
	                String nextLink = link.attr("href");

	                String regex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
	                Pattern pattern = Pattern.compile(regex);

	                // Check if the nextLink matches the URL pattern and if it has not been visited before
	                if (pattern.matcher(nextLink).matches() && visited.contains(nextLink) == false) {
	                    // Increase the level and recursively crawl the nextLink
	                    crawl(level++, nextLink, visited);
	                }
	            }
	        }
	    }
	}


	private static Document request(String targetLink, ArrayList<String> v) throws Exception {
	    try {
	        // Use the ChromeDriver to navigate to the targetLink
	        parameter2.get(targetLink);
	    } catch (Exception exception) {
	        // If an exception occurs during navigation, return null
	        return null;
	    }

	    // Variables to store flight information
	    String[] ss = null;
	    List<Integer> priceList = new ArrayList<Integer>();

	    // Attempt to fetch flight details from the webpage for a maximum of 2 attempts
	    int attempts = 0;
	    while (attempts < 2) {
	        // Find flight elements with class name "FlightsTicket_container__NWJkY"
	        List<WebElement> elements1 = parameter2.findElements(By.className("FlightsTicket_container__NWJkY"));
	        List<String> flightDetails = new ArrayList<String>();

	        // If flight elements are found on the webpage
	        if (elements1.size() > 0) {
	            for (WebElement element : elements1) {
	                // Split the text content of the element into an array of strings
	                ss = element.getText().split("\n");
	                String str = element.getText();
	                flightDetails.add(str);
	                for (int i = 0; i < ss.length; i++) {
	                    // Find the price in the element's text starting with "C$"
	                    if (ss[i].startsWith("C$")) {
	                        // Extract the numeric part of the price and add it to the priceList
	                        priceList.add(Integer.parseInt(ss[i].replaceAll("[^0-9]", "")));
	                    }
	                }
	            }

	            // Print the list of prices and find the index of the lowest price
	            System.out.println("PriceList:" + priceList);
	            int lowestPrice = priceList.indexOf(Collections.min(priceList));
	            System.out.println("Lowest Price Flight:" + flightDetails.get(lowestPrice));
	            break;
	        }
	        attempts++;
	    }

	    // Find the HTML body element of the webpage
	    WebElement element = parameter2.findElement(By.tagName("body"));
	    // Get the HTML content of the web element
	    String htmlContent = element.getAttribute("innerHTML");
	    // Convert the HTML content to a JSoup document
	    Document document = Jsoup.parse("<html><head><title>" + parameter2.getTitle() + "</title></head><body>" + htmlContent + "</body></html>");

	    return document;
	}

	public static int minDistance(String firstWrd, String secondWrd) {
	    // Assigning the length of both words to variables
	    int x = firstWrd.length();
	    int y = secondWrd.length();

	    // Create a 2D array to store edit distances
	    int[][] editDist = new int[x + 1][y + 1];

	    // Dynamic Programming algorithm to calculate edit distances
	    for (int i = 0; i <= x; i++)
	        editDist[i][0] = i;
	    for (int i = 1; i <= y; i++)
	        editDist[0][i] = i;

	    for (int i = 0; i < x; i++) {
	        for (int j = 0; j < y; j++) {
	            if (firstWrd.charAt(i) == secondWrd.charAt(j))
	                editDist[i + 1][j + 1] = editDist[i][j];
	            else {
	                int a = editDist[i][j];
	                int b = editDist[i][j + 1];
	                int c = editDist[i + 1][j];
	                editDist[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
	                editDist[i + 1][j + 1]++;
	            }
	        }
	    }

	    // Return the edit distance between the two words
	    return editDist[x][y];
	}



	static Set<String> htmlParse() {
		// fetching document
		String url = "http://www.citymayors.com/gratis/canadian_cities.html";
		Document doc;
		// adding try block to check for exceptions
		try {
			doc = Jsoup.connect(url).get();
			String body = doc.body().text();
			// adding word_var in an array
			String[] str = body.split("\\s+");
			Set<String> name = new HashSet<>();
			for (int i = 0; i < str.length; i++) {
				// deleting non alphanumeric characters
				name.add(str[i].replaceAll("[^\\w]", "").toLowerCase());
			}
			// System.out.println("Length of string is: "+ str.length);
			return name;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * This method checks for the closest word in case there is a typo, specifically for cities,
	 * by comparing the edit distance between the given indexWord and a list of cities obtained from htmlParse().
	 *
	 * @param indexWord The word to check for similarity.
	 * @return The closest word from the list of cities that has an edit distance of 2 or less, or the original word if no similar word is found.
	 */
	public static String wordCheck(String indexWord) {

	    // Using an ArrayList to store the resulting list of similar words
	    ArrayList<String> resultsArray = new ArrayList<String>();

	    // Converting the indexWord to lowercase for uniformity in comparisons
	    indexWord = indexWord.toLowerCase();

	    // Initialize minDistance with a large value
	    int minDistance = Integer.MAX_VALUE;

	    // Retrieve the parsed array of words stored in the Set from htmlParse()
	    Set<String> word_varArray = htmlParse();

	    // Loop through the word_varArray to find the words with minimum edit distance to the indexWord
	    for (String word : word_varArray) {
	        int distance = minDistance(indexWord, word);
	        if (distance <= minDistance) {
	            minDistance = distance;
	        }
	        // Condition for a word to be considered similar is having an edit distance of 2 or less
	        if (distance <= 2) {
	            resultsArray.add(word);
	        }
	    }

	    // Printing the list of similar words
	    System.out.println("Did you mean " + indexWord + ":");
	    System.out.println(resultsArray);

	    // Return the first word from the list, as it is the closest word found
	    String closestWord = resultsArray.get(0);
	    return closestWord;
	}


}