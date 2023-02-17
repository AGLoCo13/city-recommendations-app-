import java.util.ArrayList;

public class stringCounter {
	public static int countDistinctWords(String str) {
		String s[]=str.split(" ");
		ArrayList<String> list=new ArrayList<String>();
		
		for (int i = 1; i < s.length; i++) {
		    if (!(list.contains(s[i]))) {
		        list.add(s[i]);
		    }
		}
		return 	list.size();
	}
	public static int countTotalWords(String str) {	
		String s[]=str.split(" ");
		return 	s.length;
	}	
	public static int countCriterionfCity(String cityArticle, String criterion) {
		cityArticle=cityArticle.toLowerCase();
		int index = cityArticle.indexOf(criterion);
		int count = 0;
		while (index != -1) {
		    count++;
		    cityArticle = cityArticle.substring(index + 1);
		    index = cityArticle.indexOf(criterion);
		}
		return count;
	}
	
	
	
	}
		
		
	
		



