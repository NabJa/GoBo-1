package GoBi1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


/*
 * Reads in txt file
 */
public class GFFimport extends Annotation {

	/*
	 * Constructor from Annotation
	 */
	public GFFimport(String chromosome, String feature, String type, String start, String end, String score,
			String strand, String frame, String[] attribute) {
		super(chromosome, feature, type, start, end, score, strand, frame, attribute);
	}

	public static ArrayList<Annotation> giveAll(String myPath) throws FileNotFoundException {
		
		File file = new File(myPath); // "C:\\Users\\Anja\\Desktop\\GoBi\\gtfRawEasy.txt"
		Scanner sc = null;
		String[] line = new String[8];
		ArrayList<Annotation> myData = new ArrayList();
		
		try {
			sc = new Scanner(file);

			while (sc.hasNextLine()) {
				
				line = new String[8];
				line = sc.nextLine().split("\t");

				if (line.length > 1) {
					String[] attris = line[8].split(";");
					
					Annotation myRead = new Annotation(line[0], line[1], line[2], line[3], line[4], line[5], line[6],
							line[7], attris);
					
					myData.add(myRead);
					

					/*
					 * This would print all the values seperated by tabs
					 */
//					 for (int i = 0; i < line.length-1; i++) {
//					 line[i] = line[i].trim();
//					 System.out.println(line[i]);
//					 }	

					
					/*
					 * This would print all the values saved in line[8] and seperated by ";"
					 */
//					 for (int i = 0; i < attris.length; i++) {
//					 line[i] = line[i].trim();
//					 System.out.println(attris[i]);
//					 }	
					 
				}
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
		return myData;
	}
	
	
}
