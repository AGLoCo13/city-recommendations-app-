import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;
/**
 * The City class contains all needed information for creating needed City objects (Constructors , setters , getters , etc.)
 * The City class also contains the method VectorRepresentation which takes a City object  and normalizes its features according to Deliverable 1
 * @author AGLoCo
 *
 */
public class City {
	
	private float geodesicDistance;
	private String timestamp;
	private List<City> cityList;
	/**
	 * 
	 * @param cityName is the string parameter containing the name of the city 
	 * @param country stores the city's country
	 * @param cafe stores the city's cafes
	 * @param sea  stores the city's seas if the city has 
	 * @param restaurant stores the city's restaurants
	 * @param museum stores the city's museums 
	 * @param stadium stores the city's restaurants
	 * @param mountain stores the city's mountains
	 * @param etc stores the city's etc points of interest
	 * @param temperature stores the city's temperature
	 * @param latitude stores the city's latitude
	 * @param longtitude stores the city's longtitude
	 * @param clouds stores the city's cloud percentage
	 * @param timestamp stores the date and time A city was searched
	 */
	public City(String cityName, String country, float cafe, float sea, float restaurant, float museum, float stadium,
			float mountain, float etc, float temperature, float latitude, float longtitude, float clouds , String timestamp) {
		super();
		this.cityName = cityName;
		this.country = country;
		this.cafe = cafe;
		this.sea = sea;
		this.restaurant = restaurant;
		this.museum = museum;
		this.stadium = stadium;
		this.mountain = mountain;
		this.etc = etc;
		this.temperature = temperature;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.clouds = clouds;
		this.timestamp = timestamp;
	
	}
	/**
	 * Setter and Getter of City's timestamp 
	 * @param timestamp
	 */
	private void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * Created another Constructor of City Object in order to store the cities that are to be recommended to the user.The difference between this constructor
	 * and the first is that this doesn't have the parameters of latitude and longtitude.Instead it contains a parameter named geodesicDistance which stores
	 * the normalized geodesic distance 
	 * @param cityName store's the Recommended City's CityName
	 * @param country store's the Recommended City's country
	 * @param cafe store's the Recommended City's cafes
	 * @param sea store's the Recommended City's seas
	 * @param restaurant store's the Recommended City's restaurants
	 * @param museum store's the Recommended City's museums
	 * @param stadium store's the Recommended City's stadiums
	 * @param mountain store's the Recommended City's mountains
	 * @param etc store's the Recommended City's etc points of interest
	 * @param temperature store's the Recommended City's temperature in fahrenheit
	 * @param clouds store's the Recommended City's clouds percentage
	 * @param geodesicDistance store's the Recommended City's
	 */
	public City(String cityName, String country, float cafe, float sea, float restaurant, float museum, float stadium,
			float mountain, float etc, float temperature, float clouds,float geodesicDistance) {
		this.cityName=cityName;
		this.country=country;
		this.cafe=cafe;
		this.sea = sea;
		this.restaurant = restaurant;
		this.museum = museum;
		this.stadium=stadium;
		this.mountain = mountain;
		this.etc = etc;
		this.temperature = temperature;
		this.clouds = clouds;
		this.geodesicDistance = geodesicDistance;
	}
	public List<City> getList(){
		return cityList;
	}
	public void setCityToList(City city) {
		cityList.add(city);
	}
	/**
	 * 
	 * @param geodesicDistance setter of the geodesic distance
	 */
	private void setGeodesicDistance(float geodesicDistance) {
		this.geodesicDistance = geodesicDistance;
	}
	
	int n=10;
	String cityName,country;
	float cafe,sea,restaurant,museum,stadium,mountain,etc,temperature,latitude,longtitude,clouds;
	/**
	 * This method is used to Connect to wikipedia and OpenWeatherMap API respectively , fetch the JSON files and parse from them the features we need (cafes,seas,etc..)
	 * to a City object.
	 * @param city takes the string of the name of the city to be searched 
	 * @param country takes the string of the city's country that is to be searched (example. Greece = "gr")
	 * @param appid takes the string of my personal APPID given to me by OpenWeatherAPI
	 * @throws IOException on input error 
	 */
	public void RetrieveData(String city, String country, String appid) throws  IOException {
		 SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
		 ObjectMapper mapper = new ObjectMapper(); 
		 //Initializing connection with OpenWeatherMap API
		 OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+","+country+"&APPID="+appid+""), OpenWeatherMap.class);
		 //Getting the temperature and assigning it to a float value
		 //Getting the latitude and assigning it to a float value
		 //Getting the longitude and assigning it to a float value
		 temperature = weather_obj.getMain().getTemp().floatValue();
		 latitude=weather_obj.getCoord().getLon().floatValue();
		 longtitude=weather_obj.getCoord().getLat().floatValue();
		 //clouds percentage is taken from the getClouds object, i first convert it to String
		 clouds = weather_obj.getClouds().getAll().floatValue();
		 //Then,to float
		  //initializing connection with MediaWiki API
		 MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2"),MediaWiki.class);
		 //Getting the Json and parsing it to a String named WikiJson
		 String wikiJson = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
		 //Using the created StringCounter class to count occurencies of city's features from JsonWiki 
		 cafe = stringCounter.countCriterionfCity(wikiJson, "cafe");
	     sea = stringCounter.countCriterionfCity(wikiJson, "sea");
	     museum = stringCounter.countCriterionfCity(wikiJson, "museum");
	     restaurant = stringCounter.countCriterionfCity(wikiJson,"restaurant");
	     mountain = stringCounter.countCriterionfCity(wikiJson, "mountain");
	     stadium = stringCounter.countCriterionfCity(wikiJson, "stadium");
	     etc = stringCounter.countCriterionfCity(wikiJson, "etc");
	     setCityName(city);
	     setCountry(country);
	     setTerms(cafe,sea,museum,restaurant,mountain,stadium,etc,temperature,clouds,latitude,longtitude);
	     setCafe(cafe);
	     setMuseum(museum);
	     setRestaurant(restaurant);
	     setMountain(mountain);
	     setStadium(stadium);
	     setEtc(etc);
	     setClouds(clouds);
	     setLatitude(latitude);
	     setLongtitude(longtitude);
	     String strDate = formatter.format(new Date());
	     setTimestamp(strDate);
	     /*cityList.add(new City(this.getCityName(),this.getCountry(),this.getCafeCount(),this.getSeaCount(),this.getRestaurantCount(),
	    		       this.getMuseumCount(),this.getStadiumCount(),this.getMountainCount(),this.getEtcCount(),this.getTemperature()
	    		       ,this.getLatitude(),this.getLongtitude())); */
	     
	}
	//Creating A setter function to set all of the terms found from wikipedia and OpenWeatherMap API
	//This setter is used in RetrieveData function to parse the found terms.
	public void setTerms(float newCafe,float newSea,float newMuseum,float newRestaurant,float newMountain, float newStadium, float newEtc , float newTemperature ,float newClouds, float newLatitude , float newLongitude ) {
		this.cafe = newCafe;
		this.sea = newSea;
		this.museum = newMuseum;
		this.restaurant = newRestaurant;
		this.mountain = newMountain;
		this.stadium = newStadium;
		this.etc = newEtc;
		this.temperature = newTemperature;
		this.latitude = newLatitude;
		this.longtitude = newLongitude;
		this.clouds=newClouds;
	}
	//City name setter
	public void setCityName(String newCity) {
		this.cityName = newCity;
	}
	//Country setter
	public void setCountry(String newCountry) {
		this.country=newCountry;
	}
	//OpenWeatherMap API key getter
	public String getApiKey(){
		String appid = "87f7ead88749ce59a63c5e0b593dccd4";
		return appid;
	} 
	//Setters of each feature
	public void setCafe(float newCafe) {
		this.cafe=newCafe;
	}
	public void setSea(float newSea) {
		this.sea = newSea;
	}
	public void setMuseum(float newMuseum) {
		this.museum = newMuseum;
	}
	public void setRestaurant(float newRestaurant) {
		this.restaurant = newRestaurant;
	}
	public void setMountain(float newMountain) {
		this.mountain = newMountain;
	}
	public void setStadium(float newStadium) {
		this.stadium = newStadium;
	}
	public void setEtc(float newEtc) {
		this.etc = newEtc;
	}
	public void setTemperature(float newTemp) {
		this.temperature = newTemp;
	}
	public void setLatitude(float newLatitude) {
		this.latitude = newLatitude;
	}
	public void setLongtitude(float newLongtitude) {
		this.longtitude = newLongtitude;
	}
	public void setClouds(float newClouds) {
		this.clouds = newClouds;
	}
	public float getGeodesicDistance() {
		return geodesicDistance;
	}
	
	//Getters for all occurencies of city's features 
	public String getCountry() {
		return country;
	}
	public String getCityName() {
		return cityName;
	}
	public float getCafeCount() {
		return cafe;
	}
	public float getSeaCount() {
		return sea;
	}
	public float getMuseumCount() {
		return museum;
	}
	public float getRestaurantCount() {
		return restaurant;
	}
	public float getMountainCount() {
		return mountain;
	}
	public float getStadiumCount() {
		return stadium;
	}
	public float getEtcCount(){
		return etc;
	}
	public float getTemperature() {
		return temperature;
	}
	public float getLatitude() {
		return latitude;
	}
	public float getLongtitude() {
		return longtitude;
	}
	public float getClouds() {
		return clouds;
	}
	/*
	 * This method is used to create a Vector Array of floats in which the features of the City that are fetched will be normalized.
	 * After Normalization the method returns A vector Array of normalized float numbers
	 *  
	 */
	public Vector<Float> vectorRepresentation(){
		float[] terms = {this.getCafeCount(),this.getSeaCount(),this.getMuseumCount(),this.getRestaurantCount(),this.getMountainCount(),this.getStadiumCount(),
				this.getEtcCount(),this.getTemperature(),this.getClouds(),this.getLatitude(),this.getLongtitude()};

		System.out.println(terms);
		//Initializing the Vector Array
		//Normalizing the features
		//TO DO so.. we need another Array named Normalized_terms
		float[] normalizedTerms;
		normalizedTerms = new float[10];
		//numbers stored from the arrays' index 0 to 6 are the features before temperature and coordinations
		//so we use the minimum to max scaler accordingly for the first 7 terms
		for (int i=0; i<6; i++) {
			normalizedTerms[i]=(terms[i])/10;
		}
		//for the normalized temperature we use a float var and assign it to the index nr.7 of the terms array
		normalizedTerms[7]=(terms[7] - 184.0f)/(284.0f - 184.0f);
		//Just following this: 
	    float normalizedClouds = terms[8]/100;
		normalizedTerms[8]=normalizedClouds;
		double geodesicDistance= distanceCalculator.distance(23.7162f, 37.9795f, terms[9], terms[10], "K");
        float Distance = Double.valueOf(geodesicDistance).floatValue();
        normalizedTerms[9]= Distance/13396;
        setGeodesicDistance(normalizedTerms[9]);
        //13396 the distance in kilometres between Athens and Sydney
        //variable of 23.7162 stands for the Athens' Latitude , as 37.9795 for the Longtitude
	    Vector<Float> features = new Vector<Float>();
    	 for (int i=0; i<10; i++) {
    		 features.add(normalizedTerms[i]);
    	 }
    	     System.out.println(features);
    		 return (Vector<Float>) features;
    		 
    		 
     }
	
}
	
	

