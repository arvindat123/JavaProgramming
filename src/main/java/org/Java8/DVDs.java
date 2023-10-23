package org.Java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DVDs {
	public static void main(String[] args) {
		List<DVDInfo> dvds = loadDVDs("dvdinfo.txt");
		dvds.forEach(System.out::println);
	}
	
	public static List<DVDInfo> loadDVDs(String filename)
	{
		List<DVDInfo> dvds = new ArrayList<>();
		//Stream a file , line by line
		try
		{
			Stream<String> stream = Files.lines(Paths.get("D:\\EclipseWorkspace\\JavaLearning\\JavaExamples\\src\\com\\JavaTest\\Java8\\"+filename));
			stream.forEach(line -> {
				String[] dvdItems = line.split("/");
				DVDInfo dvd = new DVDInfo(dvdItems[0], dvdItems[1], dvdItems[2]);
				dvds.add(dvd);
			});
			
		}
		
		catch (IOException e) {
			System.out.println("Error reading DVDs");
			e.printStackTrace();
		}
		return dvds;
	}
}
