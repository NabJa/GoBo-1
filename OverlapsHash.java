package GoBi1;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;

public class OverlapsHash extends Gene {

	public OverlapsHash(String id, int start, int end, String strand, String type, String name) {
		super(id, start, end, strand, type, name);
	}

	// path is stored in variable file. Need to be changed for user input!
	File file = new File("C://Users/Anja//Desktop//GoBi//bspGTFraw.txt");

//	if(checkExist(file) == true) {
//		
//	}
	
	// HashMap has to store String key (gene_id) and String value (rest of Gene)
	public HashMap<String, int[]> readFile(String file) throws IOException {

		HashMap<String, int[]> read = new HashMap<String, int[]>();

		FileInputStream inputStream = null;
		Scanner sc = null;

		try {

			inputStream = new FileInputStream(file);
			sc = new Scanner(inputStream, "UTF-8");

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] geneValues = line.split("\t");

				Annotation myRead = new Annotation(geneValues[0], geneValues[1], geneValues[2], geneValues[3],
						geneValues[4], geneValues[5], geneValues[6], geneValues[7], geneValues[8]);

				// int start = Integer.parseInt(geneValues[1]);
				// int end = Integer.parseInt(geneValues[2]);
				// Gene myRead = new Gene(geneValues[0], start, end, geneValues[3],
				// geneValues[4], geneValues[5]);

				read.put(Gene.getID(), Gene.getLoc());
			}

			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}

		return read;
	}

	
	public boolean checkExist(File file) {
		boolean bol = file.exists();

		if (bol == true) {
			return true;
		} else {
			return false;
		}
	}
}
