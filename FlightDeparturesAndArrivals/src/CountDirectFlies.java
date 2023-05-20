import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CountDirectFlies { 
	public static void main(String[] args) {
		try {
			String fileName = "FlightDeparturesAndArrivals/data/Departure_Arrival_CityCode_Direct.csv";
//			Files.lines(Paths.get(fileName))
//			.distinct()
//			.sorted()
//            .map(l -> l.split(","))
//    		.filter(s -> s[2].equals("0") )
//    		.filter(s -> s[0].equals("PHF") )
//			.forEach( l -> System.out.println(l[0]+","+l[1]+","+l[2]));

			
			
	        Map<String, List<String>> totalByAirp = 
	        	Files.lines(Paths.get(fileName))
	                .map(l -> l.split(","))
	        		.filter(s -> s[2].equals("0") )
	        		.collect(Collectors.groupingBy(s -> s[0],Collectors.mapping(l -> l [1], Collectors.toList())));
	        
	        
	        SortedMap<String, List<String>> totalByAirpSorted = new TreeMap<String, List<String>>(totalByAirp);

	        totalByAirpSorted.forEach(	        		
	        		(k,e) -> {
	        			String destinations =
	        				e.stream()
	        				.distinct()
	        				.sorted()
	        				.collect(Collectors.joining("-"));	        				
//	        				.reduce("" , (s, x) -> s.equals("")?x :s + "-" + x);
	        			
	        			long numDestinations = e.stream().distinct().count();

	        			System.out.println(k + "," + numDestinations + "," + destinations);
	        			}
	        		);
	        
	        		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
