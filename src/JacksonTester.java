import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Format.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
/**
 * For Q2.3 i created a new class named JacksonTester that will be  used in order to R/W to the JSON of Cities
 * @author AGLoCo
 *
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonTester {
	 
	@SuppressWarnings("unchecked")
	
	/**
	 * Method WriteToJson Writes the City object to a json file called cities.json
	 * @param city is the city object that must be written to the cities.json file
	 * @throws JSONException on JSON file error
	 * @throws IOException  on input error
	 * @throws ParseException on parsing error
	 */
	public void WriteToJson(City city) throws JSONException, IOException, ParseException {
	            // Create a new JSONObject
			
		        JSONParser parser = new JSONParser();
		        JSONObject outer = new JSONObject();
		        JSONObject inner = new JSONObject();
		        JSONObject data = new JSONObject();
		        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
	            inner.put("cityName",city.getCityName());
	            inner.put("country",city.getCountry());
	            inner.put("cafe", city.getCafeCount());
	            inner.put("Seas",city.getSeaCount());
	            inner.put("restaurant",city.getRestaurantCount());
	            inner.put("museums",city.getMuseumCount());
	            inner.put("stadiums",city.getStadiumCount());
	            inner.put("mountains",city.getMountainCount());
	            inner.put("etc",city.getEtcCount());
	            inner.put("temperature",city.getTemperature());
	            inner.put("latitude",city.getLatitude());
	            inner.put("longtitude",city.getLongtitude());
	            inner.put("clouds",city.getClouds());
	            inner.put("timestamp", city.getTimestamp());
	            arr.add(inner);
                outer.put("City",arr);
                File file = new File("cities.json");
                if(file.createNewFile()) {
                   System.out.println("File: " +file.getName() + " created");
                   PrintWriter write = new PrintWriter(new FileWriter(file));
                   write.write(outer.toJSONString());
                   write.close();
                }else{
                    Object obj = parser.parse(new FileReader("cities.json"));
                    JSONObject jsonObject = (JSONObject) obj;
                    JSONArray array = (JSONArray) jsonObject.get("City");
                    PrintWriter write = new PrintWriter(new FileWriter(file));
                    Iterator<JSONObject> iterator = array.iterator();
                    while(iterator.hasNext()) {
                        JSONObject it = iterator.next();
                        data = (JSONObject) it; 
                        arr.add(data);
                        } 
                    write.write(outer.toJSONString());
     
                    write.close();
            }
         
        	 
         }
	/**
	 * The below method reads from JSON file , deserializes the json into City objects and returns a List of Cities that are written in it
	 * @return The List of cities that are stored in JSON file 
	 * @throws JsonParseException on parsing JSON error
	 * @throws JsonMappingException on mapping the JSON error
	 * @throws IOException on input error
	 * @throws ParseException 
	 */
	
	public List<City> readJSON() throws JsonParseException, JsonMappingException, IOException, ParseException {
		/*JSONParser parser = new JSONParser();
		JSONObject a = (JSONObject) parser.parse(new FileReader("cities.json"));
		JSONArray cities = (JSONArray)a.get("City");
		List<City> cityList = new ArrayList<City>();
		for (Object c : cities) {
			JSONObject city = (JSONObject) c;
			String cityName = (String) city.get("cityName");
			String country = (String) city.get("country");
			Float cafes = (float) city.get("cafe");
			Float seas = (Float) city.get("Seas");
			Float restaurant = (Float) city.get("restaurant");
			Float stadiums = (Float) city.get("stadiums");
			Float etc = (Float) city.get("etc");
			Float mountains = (Float) city.get("mountains");
			Float museums = (Float) city.get("museums");
			Float temperature = (Float) city.get("temperature");
			Float latitude = (Float) city.get("latitude");
			Float longtitude = (Float) city.get("longtitude");
			Float clouds = (Float) city.get("clouds");
			String timestamp = (String) city.get("timestamp");
			cityList.add(new City(cityName,country,cafes,seas,restaurant,museums,stadiums,mountains,etc,temperature,latitude,longtitude,clouds, timestamp));
		} 
		for ( City c : cityList) {
			System.out.println(c.getSeaCount());
		}
		return cityList;
		*/
		/*byte[] mapData = Files.readAllBytes(Paths.get("cities.json"));
		City[] cityArr = null;
		ObjectMapper mapper = new ObjectMapper();
		cityArr = mapper.readValue(mapData,City[].class);
		List <City> cityList = Arrays.asList(cityArr);
		for (int i=0; i<cityList.size(); i++) {
			System.out.println("City"+i+"\n"+cityList.get(i));
		}
		return cityList; */
		
	    File file = new File("cities.json");
	    List<City> cities = new ArrayList<City>();
	    if(file.exists()) {
	    	
	    	JsonReader reader = new JsonReader(new FileReader("cities.json"));
		    GeneralInfo generalInfo = new Gson().fromJson(reader, GeneralInfo.class);
		    cities = generalInfo.getList();
		    /*System.out.println("JSON FILE CONTAINSS:\n");
		    for(City c: cities) {
		    	System.out.println(c.getCafeCount());
		    }
		    System.out.println("\nEND"); */
	    }else {
	    	System.out.println("Warning! File cities.json doesn't exist");
	    }
		return cities;  
		
	}
	/**
	 * Q.2.6 Make a map where it will have as key days of the week and as value the names of the City that were added to the system on the respective day.
	 * @throws java.text.ParseException
	 */
	public void dayAdded() throws java.text.ParseException, ParseException {
		JacksonTester tester = new JacksonTester();
		List<City> CandidateCities = new ArrayList<City>();
		try {
			CandidateCities = tester.readJSON();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String,String> dayOfCityAdded = new HashMap<String,String>();
		for (City c : CandidateCities) {
			dayOfCityAdded.put(c.getTimestamp(),c.getCityName());
		}
		for (String i : dayOfCityAdded.keySet()) {
			System.out.println("key: "+ i + " value: " + dayOfCityAdded.get(i));
		}
		
		
	}
	//Added method returns an Array of Strings of Cities that are included in cities.json
	//Used to show the results on the UI
	ArrayList<String> Added() throws java.text.ParseException , ParseException{
		JacksonTester tester = new JacksonTester();
		ArrayList<String> stringList = new ArrayList<String>();
		List<City> CandidateCities = new ArrayList<City>();
		try {
			CandidateCities = tester.readJSON();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HashMap<String,String> dayOfCityAdded = new HashMap<String,String>();
		for (City c : CandidateCities) {
			dayOfCityAdded.put(c.getTimestamp(), c.getCityName());
	     }
		for (String i: dayOfCityAdded.keySet()) {
			stringList.add("key: " +i+ " value: " +dayOfCityAdded.get(i));
		}
		return stringList;
		
		}
	}
	
	


	


