package gobi;
//import.package augmentedTree

import java.io.File;
import java.util.Scanner;

public class GTFRead {

	String path; // "C:\\Users\\Anja\\Desktop\\GoBi\\gtfRawEasy.txt"

	public GTFRead(String path) {
		this.path = path;
	}

	
	/**
	 * Reads file gene by gene. 
	 * Hierarchy: Gene -> RegionVector -> Region
	 * Hierarchy order must be given by file.
	 */
	public void getGenes() {

		File file = new File(path);

		Scanner sc = null;

		Gene gene = new Gene();
		RegionVector currentRV = new RegionVector();

		try {
			sc = new Scanner(file);

			//jumps over first annotation starting with "#"
			while (sc.hasNextLine() && sc.nextLine().startsWith("#")) {
				continue;
			}
			
			while (sc.hasNextLine()) {

//				int i = 0;
//				System.out.println(i);
//				i++;

				//Splits after tab and ";"
				String[] line = sc.nextLine().split("(\t)|(;)");
				
				String source = line[1];
				String type = line[2];
				int start = Integer.parseInt(line[3]); // line[3] is always start and should be read as integer
				int end = Integer.parseInt(line[4]); // line[3] is always end and should be read as integer
				String geneID = line[8];
				String strand = line[6];
				String proteinID = line[18];

				if (type.toLowerCase().equals("cds")) {
					Region cds = new Region(start, end, proteinID);
					currentRV.addRegion(cds);
				} else if (type.toLowerCase().equals("transcript")) {
					RegionVector newRV = new RegionVector(line[9], start, end);
					gene.insertRV(newRV);
					currentRV = newRV;
				} else if (type.toLowerCase().equals("gene")) {
					//gene.printTranscriptsInverse(); //DOESNT PRINT FIRST GENE					
					gene.setGene(geneID, start, end, strand, source, type); // creates new gene with new hashMap for transcripts
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("got error xxx while doing yyy", e);
		}
		gene.printTranscriptsInverse(); //DOESNT PRINT FIRST GENE					

		sc.close();
	}

}