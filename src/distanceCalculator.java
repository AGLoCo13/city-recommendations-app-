/**
 * The class distanceCalculator is used in VectorRepresentation method of the City class in order to calculate the GeodesicDistance between 2 cities.
 * @author AGLoCo
 *
 */
public class distanceCalculator {
	/**
	 * 
	 * @param lat1 is the first city's latitude
	 * @param lon1 is the first city's longtitude
	 * @param lat2 is the second city's latitude
	 * @param lon2 is the second city's longtitude
 	 * @param unit is the way the distance will be displayed , takes K for kilometres or N for Nautical miles 
	 * @return The calculated distance
	 */
	public static double distance(float lat1, float lon1, float lat2, float lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			float theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} else if (unit.equals("N")) {
				dist = dist * 0.8684;
			}
			return (dist);
		}
	}
}
