import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
/**
 * The class OpenData implements the interface of the perceptrons in order to recommend to the user cities , based on his age and some 
 * different weights applied for the young , middle , and elder traveller respectively.
 * It also contains the main method of the Program , so the whole program runs from here. 
 * @author AGLoCo
 *
 */


public class OpenData implements PerceptronTraveller { 
	public static final ArrayList<City> recommendedCities = new ArrayList<City>();
	public static List<City> CandidateCities= new ArrayList<City>();
	//Q2.3 Create a JacksonTester object to R/W from/to JSON
	JacksonTester tester = new JacksonTester();
	/*
	 * This method is used to get A List of City Objects from the JSON file 
	 * It uses the JacksonTester class and its method readJSON to read the List of Cities 
	 * @returns the Candidates cities List 
	 */
	public List<City> getCitiesFromJson() throws JsonParseException, JsonMappingException, IOException, ParseException {
		CandidateCities = tester.readJSON();
		return CandidateCities;
	}
	/**
	 * This method is used to insert a new city to The Candidates Cities.
	 * It reads the JSON file , then parses the List of Cities Provided by the JSON to an arrayList of Candidate cities.
	 * It also checks if the City to be inserted is already inserted in the JSON file using equals() method .If the City is already inserted it notifies
	 * the user that the City is already in the file and prompts him to insert another 
	 * @param scanner gets a Scanner class object which is able to get input of the city name and country from the user. 
	 * @throws IOException on input error.
	 * @throws JSONException on JSON read/write error.
	 * @throws ParseException on parsing errors.
	 */
	
	public void insertNewCity(Scanner scanner) throws IOException, JSONException, ParseException  {
		CandidateCities = tester.readJSON();
		String cityName;
		String country;
		String appid = "87f7ead88749ce59a63c5e0b593dccd4";
		    
				System.out.println("Please Provide a Candidate city name:\n");
				cityName = scanner.nextLine();
				System.out.println("Please Provide a country (ex.greece = gr)");
				country = scanner.nextLine();
			
	    if (CandidateCities.isEmpty()) {
	    	City city = new City(cityName, country, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f , null);
		    city.RetrieveData(cityName, country, appid);
		    CandidateCities.add(city);
		    tester.WriteToJson(city);
		    System.out.println("City " +city.getCityName()+ " successfully added to cities.json!");
		}else { 
		    for(City c : CandidateCities) {
			          while(c.getCityName().equals(cityName)) {
			        	  System.out.println("City already added at Candidate City List (@cities.json) on:\n");
			        	  System.out.println(c.getTimestamp()+"\n");
			        	  System.out.println("Please Provide a Candidate city name:\n");
			        	  cityName = scanner.next();
			        	  System.out.println("Please Provide a country (ex.greece = gr)");
			        	  country = scanner.next();
			          } 
			          c = new City(cityName, country, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
			      	  c.RetrieveData(cityName, country, appid);
			      	  tester.WriteToJson(c);
			      	  System.out.println("City " +c.getCityName()+ " succesfully added to cities.json file!");
			      	  break;
			  }
		    
			}
		}
	//Overloaded insertNewCity Method that is Used on the UI
	public Boolean insertNewCity(String cityName,String country) throws IOException, JSONException, ParseException {
		CandidateCities = tester.readJSON();
		Boolean response = null;
		String appId = "87f7ead88749ce59a63c5e0b593dccd4";
		if (CandidateCities.isEmpty()) {
	    	City city = new City(cityName, country, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , null);
		    city.RetrieveData(cityName, country, appId);
		    CandidateCities.add(city);
		    tester.WriteToJson(city);
		    response = true;
		    
		}else {
			for(City c : CandidateCities) {
		          while(c.getCityName().equals(cityName)) {
		        	  response = false;
		        	  return response;
		          }
		          c = new City(cityName, country, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		      	  c.RetrieveData(cityName, country, appId);
		      	  tester.WriteToJson(c);
		      	  response = true;
		      	  break;
		          }
			}
		return response;
		}
		
		
		
	
	//Getter of the CandidatesCities List
	public List<City> getCandidateCities() {
		return CandidateCities;
	}
	Vector<Float> weightBias = new Vector<Float>();
	//Implementation of recommend method of the interface PerceptronTraveller
	public ArrayList<City> recommend(){
		//Created a threshold of 3 . In case the weighted_sum exceeds the threshold ,the City will be recommended
		
		//Created an Array list of Cities
    	List<City> cities = new ArrayList<City>();
    	
    	for (int i=0; i<getCandidateCities().size(); i++){
    		//Parsing the Candidate cities to the cities ArrayList 
    	cities=getCandidateCities();
    	}
    	
    	
    	//for each city of the cities Arraylist
    	for (City city : cities) {
    		
    		float weighted_sum=0;
    		//Creating a Vector of cityTerms . This Vector will be used in order to parse 
    		//the vectorRepresantation of each city's features to it.
    		Vector<Float> cityTerms = new Vector<Float>();
    		cityTerms=city.vectorRepresentation();
    		for (int i=0; i<cityTerms.size(); ++i) {
    			//Calculating the sum of weights to then filter the weighted_sum to the Step Function
    	      weighted_sum += (cityTerms.get(i) * weightBias.get(i));
    	      
      		}
    		//Heavyside stepFunction
    		//This is the Step Function in which the city will be added to the recomendedCities ArrayList 
    		//Only and only if the weighted_sum exceeds the threshold
    		if ( weighted_sum>0.5f) {
      			recommendedCities.add(city);	
    		}
    		
    		
    		
    		
    	}
    	return recommendedCities;
    	
    	
		
	}
	public String recommend(boolean exp){
		if (exp==true) {
			return recommendedCities.toString().toUpperCase();
		}else {
			return recommendedCities.toString().toLowerCase();
		}
	}
	/**
	 * This is the Main Function from which the program runs.
	 * It prompts the user of the program to choose an option from 1 to 3
	 * Based on the user's option , it makes use: Of the OpenData class and its methods , of the JacksonTester Class and its methods and of the PerceptronTraveller Interface
	 * @param args Unused
	 * @throws IOException on input error.
	 * @throws JSONException on JSON Read/Write error.
	 * @throws ParseException on parsing error.
	 */

public static void main(String[] args) throws IOException, JSONException, ParseException {
	GUI window = new GUI();
	window.MyFrame();
	OpenData ob = new OpenData();
	Scanner scanner = new Scanner(System.in);
	System.out.println("Please choose an option:\n1.Insert a City\n2.Recommend Cities to User\n3.Present Day of City addition to the list");
	Scanner sc = new Scanner(System.in);
	int option = sc.nextInt();
	if (option == 1) {
		ob.insertNewCity(scanner);
	}else if(option == 2 ) {
		JacksonTester test = new JacksonTester(); 
		List<City> Candidates = new ArrayList<City>();
		Candidates = test.readJSON();
		System.out.println("Candidate Cities:");
		for (City c : Candidates ) {
			System.out.println(c.getCityName());
		}
		
		//Prompting the User to insert his/her age */
		System.out.println("Please Enter your age , and i'll recommend you some of them! :\n");
		try (Scanner sc1 = new Scanner(System.in)) {
			int age = sc1.nextInt();
			//Checking if the user enters below zero age , too big number of age or age below 16
			while ( age>115 || age<16) {
				System.out.println("Invalid Number! Please provide an integer of your age:\n");
			    age=sc1.nextInt();
			}
			if(age>=16 && age<=25) {
				PerceptronYoungTraveller young = new PerceptronYoungTraveller();
				young.recommend();
				young.sortRecommendations();
				
			}else if(age>25 && age<=60) {
				PerceptronMiddleTraveller middle = new PerceptronMiddleTraveller();
				middle.recommend();
				middle.sortRecommendations();
				
			}else {
				PerceptronElderTraveller elder = new PerceptronElderTraveller();
				elder.recommend();
				elder.sortRecommendations();
				
			}
		}
	}else if (option==3){
		JacksonTester presentDays = new JacksonTester();
		try {
			presentDays.dayAdded();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		System.out.println("Wrong Option!");
	}
    scanner.close();
    sc.close();
	
	 
	 
	
	
	
	

	
}
}