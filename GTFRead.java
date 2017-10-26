package gobi;
//import.package augmentedTree

import java.io.File;
import java.util.Scanner;
import augmentedTree.IntervalTree;

public class GTFRead {

	String path; // "C:\\Users\\Anja\\Desktop\\GoBi\\gtfRawEasy.txt"

	public GTFRead(String path) {
		this.path = path;
	}

	/*
	 * Reads in file gene by gene. Saves everything in gene and must be directly
	 * used.
	 */
	public void getGenes() {

		File file = new File(path);

		Scanner sc = null;

		String[] line = new String[8];

		try {
			sc = new Scanner(file);

			while (sc.hasNextLine()) {

				line = new String[8];
				line = sc.nextLine().split("\t"); // fills in String line with the line seperated by tabs besser:
													// .indexOf(substring)
				String[] attributes = line[8].split(";"); // line[8] is are the attributes (=tags) of the read.
															// Seperated by ";"

				if (line.length > 1) { // because of line break in nextLine lines with no value are produced. could be
										// written after line = sc.nextLine(). But here for overview

					int start = Integer.parseInt(line[3]); // line[3] is always start and should be read as integer
					int end = Integer.parseInt(line[4]); // line[3] is always end and should be read as integer

					Gene currentGene = new Gene("", 0, 0, "", "", "");
					Transcript currentTrans = new Transcript();

					/*
					 * If we have a gene create Gene and set it to current Gene. If we have
					 * Transcript create transcript and set it to current Transcript and insert it
					 * to Gene If we have exon create RegionVector and insert it to transcript
					 */
					if (line[2].toLowerCase() == "gene") {
						Gene newGene = new Gene(attributes[0], start, end, line[6], line[1], line[2]);
						currentGene = newGene;

					} else if (line[2].toLowerCase() == "transcript") {
						Transcript newTrans = new Transcript();
						newTrans.setTranscript(attributes[1], start, end);
						currentTrans = newTrans;
						currentGene.insertTranscript(newTrans);
						
					} else if (line[2].toLowerCase() == "cds") {
						RegionVector cds = new RegionVector(attributes[2], start, end); 	// +- Frame bei start und end
																							// also z.b.
																							// start+-line[8]???
						currentTrans.insertCDS(cds);
					}

				}

			}

		} catch (Exception e) {
			throw new RuntimeException("got error xxx while doing yyy", e);
		}
		sc.close();
	}

	
	public boolean hasSkipped(Transcript wt, Transcript sv) {
		
		wt.calculateIntrons();
		sv.calculateIntrons();
		
		if()
		
		
		return true;
	}
}