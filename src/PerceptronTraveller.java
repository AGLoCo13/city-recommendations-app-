import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public interface PerceptronTraveller {
	//public ArrayList<String> recommend(float threshold,Vector<Float> AgeWeight); //Interface method
	public ArrayList<City> recommend(); 
//method recommend of Young,Middle, Elder Traveller
}    	

class PerceptronYoungTraveller implements PerceptronTraveller , Comparator<City>{
	 //public  ArrayList<String> recommend(boolean exp);
	 Vector<Float> weightBias = new Vector<Float>();
	 ArrayList<City> recommendedCities = new ArrayList<City>();
	 OpenData youngTraveller = new OpenData();
	 //This setter will be Used to parse values from GUI Textfields to the Vector of WeightBias
	 public void weightBiasSetter(float cafe , float sea , float restaurant , float museum , float stadium ,float mountain , float etc ) {
		 weightBias.add(cafe);
		 weightBias.add(sea);
		 weightBias.add(restaurant);
		 weightBias.add(museum);
		 weightBias.add(stadium);
		 weightBias.add(mountain);
		 weightBias.add(etc);
	 }
	 public ArrayList<City> recommend(){
		 List<City> candidateCities = null;
		try {
			candidateCities = youngTraveller.getCitiesFromJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 float threshold = -0.2f;
		 /*weightBias.add(-0.1f);
	     weightBias.add(-0.2f);
	     weightBias.add(-0.1f);
	     weightBias.add(0.5f);
	     weightBias.add(0.6f);
	     weightBias.add(-0.1f);
	     weightBias.add(0.3f); */
		 //HardCoded weightBias for Temperature , Clouds and Geodesic Distance
	     weightBias.add(0.6f);
	     weightBias.add(-0.2f);
	     weightBias.add(0.1f);
	    	
	    	
	    	//for each city of the cities Arraylist
	    	for (City city : candidateCities) {
	    		
	    		float weighted_sum=0;
	    		//Creating a Vector of cityTerms . This Vector will be used in order to parse 
	    		//the vector Represantation of each city's features to it.
	    		Vector<Float> cityTerms = new Vector<Float>();
	    		cityTerms=city.vectorRepresentation();
	    		for (int i=0; i<cityTerms.size(); ++i) {
	    			//Calculating the sum of weights to then filter the weighted_sum to the Step Function
	    	      weighted_sum += (cityTerms.get(i) * weightBias.get(i));
	    	      
	      		}
	    		//Heavyside stepFunction
	    		//This is the Step Function in which the city will be added to the recomendedCities ArrayList 
	    		//Only and only if the weighted_sum exceeds the threshold
	    		if ( weighted_sum>threshold) {
	      			recommendedCities.add(city);	
	    		}
	    		
	    		
	    		
	    		
	    	}
			return recommendedCities;
			
	 }
	 @Override
		//Compare cities via geodesic Distance on Young Traveller
		public int compare(City c1, City c2) {
			if(c1.getGeodesicDistance()<c2.getGeodesicDistance()) return -1;
			if (c1.getGeodesicDistance()>c2.getGeodesicDistance()) return 1;
			else return 0;
		}
	 public void sortRecommendations() {
		 PerceptronYoungTraveller geoDistanceCompare = new PerceptronYoungTraveller();
		 Collections.sort(recommendedCities,geoDistanceCompare);
		 for (City c : recommendedCities) {
			 System.out.println(c.getGeodesicDistance()+" "+c.getCityName());
		 }
	 }
	
}

class PerceptronMiddleTraveller implements PerceptronTraveller , Comparator<City>{
	Vector<Float> weightBias = new Vector<Float>();
	 ArrayList<City> recommendedCities = new ArrayList<City>();
	 OpenData middleTraveller = new OpenData();
	//This setter will be Used to parse values from GUI Textfields to the Vector of WeightBias
	 public void weightBiasSetter(float cafe , float sea , float restaurant , float museum , float stadium ,float mountain , float etc ) {
		 weightBias.add(cafe);
		 weightBias.add(sea);
		 weightBias.add(restaurant);
		 weightBias.add(museum);
		 weightBias.add(stadium);
		 weightBias.add(mountain);
		 weightBias.add(etc);
	 }
	 public ArrayList<City> recommend(){
		 List<City> candidateCities = null;
		 try {
			 candidateCities = middleTraveller.getCitiesFromJson();
		 }catch (Exception ex){
			 ex.printStackTrace();
			 
		 }
		 float threshold = -0.5f;
		 /*weightBias.add(-0.2f);
	     weightBias.add(-0.1f);
	     weightBias.add(0.2f);
	     weightBias.add(0.6f);
	     weightBias.add(0.4f);
	     weightBias.add(0.2f);
	     weightBias.add(0.3f); */
		//HardCoded weightBias for Temperature , Clouds and Geodesic Distance
	     weightBias.add(0.5f);
	     weightBias.add(0.2f);
	     weightBias.add(0.2f);
	    	
	    	
	    	//for each city of the cities Arraylist
	    	for (City city : candidateCities) {
	    		
	    		float weighted_sum=0;
	    		//Creating a Vector of cityTerms . This Vector will be used in order to parse 
	    		//the vector Represantation of each city's features to it.
	    		Vector<Float> cityTerms = new Vector<Float>();
	    		cityTerms=city.vectorRepresentation();
	    		for (int i=0; i<cityTerms.size(); ++i) {
	    			//Calculating the sum of weights to then filter the weighted_sum to the Step Function
	    	      weighted_sum += (cityTerms.get(i) * weightBias.get(i));
	    	      
	      		}
	    		//Heavyside stepFunction
	    		//This is the Step Function in which the city will be added to the recomendedCities ArrayList 
	    		//Only and only if the weighted_sum exceeds the threshold
	    		if ( weighted_sum>threshold) {
	      			recommendedCities.add(city);	
	    		}
	    		
	    		
	    		
	    		
	    	}
	    	return recommendedCities;
	 }
	@Override
	//Compare via timestamp on Middle Traveller 
		public int compare(City city1, City city2) {
			SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
			Date date1 = null;
			Date date2 = null;
			try {
				date1 = formatter.parse(city1.getTimestamp());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				date2 = formatter.parse(city2.getTimestamp());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (date1.before(date2)) return -1;
			if (date1.after(date2)) return 1;
			else return 0; 
		}
		public void sortRecommendations() {
			PerceptronMiddleTraveller timestampCompare = new PerceptronMiddleTraveller();
			Collections.sort(recommendedCities, timestampCompare);
			for (City c : recommendedCities) {
				System.out.println(c.getTimestamp()+" "+c.getCityName());
		}
	}
	
}
	
class PerceptronElderTraveller implements PerceptronTraveller , Comparator<City> {
	Vector<Float> weightBias = new Vector<Float>();
	 ArrayList<City> recommendedCities = new ArrayList<City>();
	 OpenData elderTraveller = new OpenData();
	//This setter will be Used to parse values from GUI Textfields to the Vector of WeightBias
		 public void weightBiasSetter(float cafe , float sea , float restaurant , float museum , float stadium ,float mountain , float etc ) {
			 weightBias.add(cafe);
			 weightBias.add(sea);
			 weightBias.add(restaurant);
			 weightBias.add(museum);
			 weightBias.add(stadium);
			 weightBias.add(mountain);
			 weightBias.add(etc);
		 }
	 public ArrayList<City> recommend(){
		 List<City> candidateCities = null;
		try {
			candidateCities = elderTraveller.getCitiesFromJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 float threshold = -0.5f;
		 /*weightBias.add(-0.5f);
	     weightBias.add(-0.5f);
	     weightBias.add(0.4f);
	     weightBias.add(0.4f);
	     weightBias.add(0.5f);
	     weightBias.add(0.4f);
	     weightBias.add(0.3f); */
		//HardCoded weightBias for Temperature , Clouds and Geodesic Distance
	     weightBias.add(-0.2f);
	     weightBias.add(0.4f);
	     weightBias.add(0.2f);
	   //Created an Array list of Cities
	    	
	    	
	    	//for each city of the cities Arraylist
	    	for (City city : candidateCities) {
	    		
	    		float weighted_sum=0;
	    		//Creating a Vector of cityTerms . This Vector will be used in order to parse 
	    		//the vector Represantation of each city's features to it.
	    		Vector<Float> cityTerms = new Vector<Float>();
	    		cityTerms=city.vectorRepresentation();
	    		for (int i=0; i<cityTerms.size(); ++i) {
	    			//Calculating the sum of weights to then filter the weighted_sum to the Step Function
	    	      weighted_sum += (cityTerms.get(i) * weightBias.get(i));
	    	      
	      		}
	    		//Heavyside stepFunction
	    		//This is the Step Function in which the city will be added to the recomendedCities ArrayList 
	    		//Only and only if the weighted_sum exceeds the threshold
	    		if ( weighted_sum>threshold) {
	      			recommendedCities.add(city);	
	    		}
	    		
	    		
	    		
	    		
	    	}
	    	
	    	return recommendedCities;
	 }
	@Override
	public int compare(City c1, City c2) {
		if(c1.getGeodesicDistance()>c2.getGeodesicDistance()) return -1;
		if (c1.getGeodesicDistance()<c2.getGeodesicDistance()) return 1;
		else return 0;
	}
	public void sortRecommendations() {
		PerceptronElderTraveller geoDistanceCompare = new PerceptronElderTraveller();
		Collections.sort(recommendedCities,geoDistanceCompare);
		for (City c : recommendedCities) {
			System.out.println(c.getGeodesicDistance()+" "+c.getCityName());
		}
	}
	 

}







