package testone;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {
	
	private static List<TestSuite> tests;
	
	public static List<TestSuite> processFile (String filename) {
		Pattern pattern = Pattern.compile(",");
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream  is = cl.getResourceAsStream(filename);	
		try (Stream<String> lines = new BufferedReader(new InputStreamReader(is)).lines()) {
				tests = lines.map(line -> {
				String[] arr = pattern.split(line);
				return new TestSuite(
					Integer.parseInt(arr[0]),
					arr[1],
					Integer.parseInt(arr[2]),
					HTTPMethod.valueOf(arr[3]),
					Integer.parseInt(arr[4]),
					Integer.parseInt(arr[5]),
					arr[6],
					Integer.parseInt(arr[7]),
					arr[8]
				);
			}).collect(Collectors.toList());
				is.close();
//		System.out.println(lines);
		} catch (Exception e) {
			System.out.println("Caught exception: " + e.getMessage());
		}
		return tests;
	}

}
