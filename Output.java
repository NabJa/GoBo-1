package gobi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {

	String geneID; //
	String geneName; //
	String chr; // chromosome
	char strand; //
	int nprots; // number of annotated CDS in the gene
	int ntrans; // number of annotated transcripts in the gene
	Region sv; // the SV intron as start:end
	RegionVector wt; // the WT introns within the SV intron seperated by | as start:end
	String[] sv_prots; // IDs of the SV CDSs, seperated by |
	String[] wt_prots; // IDs of the WT CDSs, seperated by |
	int minSkippedExons;
	int maxSkippedExons; // min and max number of skipped Exons in any WT/SV pair
	int minSkippedBases;
	int maxSkippedBases; // min and max number of skipped bases in any WT/SV pair

	FileWriter file;
	BufferedWriter writer;

	public Output(String geneID, String geneName, String chr) {
		this.geneID = geneID;
		this.geneName = geneName;
		this.chr = chr;

		try {
			file = new FileWriter("C:\\Users\\Anja\\Desktop\\testOutput.txt");
			writer = new BufferedWriter(file);
		} catch (Exception e) {
			throw new RuntimeException("got error while writing output.", e);
		}
	}

	/**
	 * Print tsv-file header of categories of the Object Output.
	 * @throws Exception
	 */
	public void printHeader() throws Exception {

		try {
			writer.write("id" + "\t" + "symbol" + "\t" + "chr" + "\t" + "strand" + "\t" + "nprots" + "\t" + "ntrans"
					+ "\t" + "SV" + "\t" + "WT" + "\t" + "WT_prots" + "\t" + "SV_prots" + "\t" + "min_skipped_exons"
					+ "\t" + "max_skipped_exons" + "\t" + "min_skipped_bases" + "\t" + "max_skipped_bases");
			writer.newLine();

		} catch (Exception e) {
			throw new RuntimeException("got error while printing txt", e);
		}
	}

	/**
	 * prints a tsv-file of everything in object Output
	 * @throws Exception
	 */
	public void printOutTxt(){

		try {
			writer.write(geneID + "\t" + geneName + "\t" + chr + "\t" + strand + "\t" + nprots + "\t" + ntrans + "\t");
			
			writer.write(sv.getX1() + ":" + sv.getX2() + "\t");

			String str = "";
			for (Region r : wt.regions) {
				str += ("|" + Integer.toString(r.getX1()) + ":" + Integer.toString(r.getX2()));
			}
			if(str.length() > 1) { //maybe could be removed. only important if RegionVector is empty. Shouldnt be the case
				str = str.substring(1, str.length());
				writer.write( str  + "\t");	
			}
			
			String str1 = "";
			for (String id : wt_prots) {
				str1 += ("|" + id);
			}
			str1 = str1.substring(1, str1.length());
			writer.write( str1  + "\t");	

			String str2 = "";
			for (String id : sv_prots) {
				str2 += ("|" + id);
			}
			str2 = str2.substring(1, str2.length());
			writer.write( str2  + "\t");	

			writer.write(minSkippedExons + "\t" + maxSkippedExons + "\t" + minSkippedBases + "\t" + maxSkippedBases);
			writer.newLine();

		} catch (Exception e) {
			throw new RuntimeException("got error while printing output in txt.", e);
		}
	}

	public void closeW() {
		try {
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException("got error while closing output file.", e);
		}
	}

}
