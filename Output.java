package gobi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Output {

	String geneID; //
	String geneName; //
	String chr; // chromosome
	char strand; //
	int nprots; // number of annotated CDS in the gene
	int ntrans; // number of annotated transcripts in the gene
	Region sv; // the SV intron as start:end
	RegionVector wt; // the WT introns within the SV intron seperated by | as start:end
	ArrayList<String> sv_prots = new ArrayList<String>(); // IDs of the SV CDSs, seperated by |
	ArrayList<String> wt_prots = new ArrayList<String>(); // IDs of the WT CDSs, seperated by |
	int minSkippedExons;
	int maxSkippedExons; // min and max number of skipped Exons in any WT/SV pair
	int minSkippedBases;
	int maxSkippedBases; // min and max number of skipped bases in any WT/SV pair

//	public Output(String outputDestination) {
//
//		try {
//			file = new FileWriter(outputDestination);
//			writer = new BufferedWriter(file);
//		} catch (Exception e) {
//			throw new RuntimeException("got error while writing output.", e);
//		}
//	}

	public void addSV_prots(String id) {
		sv_prots.add(id);
	}

	public void getAllSVProtIDs(RegionVector rv) {
		for(Region r : rv.regions) {
			if(!sv_prots.contains(r.regionID))
			sv_prots.add(r.regionID);
		}
	}
	
	public void addWT_prots(String id) {
		wt_prots.add(id);
	}

	public void getAllWTProtIDs(RegionVector rv) {
		for(Region r : rv.regions) {
			if(!wt_prots.contains(r.regionID)) {
				wt_prots.add(r.regionID);
			}
		}
	}
	
	public void addWT(Region r) {
		wt.addRegion(r);
	}

	public void insertMinSkippedExons(int mse) {
		if (mse < minSkippedExons) {
			mse = minSkippedExons;
		}
	}

	public void insertMaxSkippedExons(int mxse) {
		if (mxse > maxSkippedExons) {
			mxse = maxSkippedExons;
		}
	}

	public void insertMinSkippedBases(int msb) {
		if (msb < maxSkippedExons) {
			msb = maxSkippedExons;
		}
	}

	public void insertMaxSkippedBases(int mxsb) {
		if (mxsb > maxSkippedExons) {
			mxsb = maxSkippedExons;
		}
	}

	/**
	 * Print tsv-file header of categories of the Object Output.
	 * 
	 * @throws Exception
	 */
//	public void printHeader(){
//
//		try {
//			writer.write("id" + "\t" + "symbol" + "\t" + "chr" + "\t" + "strand" + "\t" + "nprots" + "\t" + "ntrans"
//					+ "\t" + "SV" + "\t" + "WT" + "\t" + "WT_prots" + "\t" + "SV_prots" + "\t" + "min_skipped_exons"
//					+ "\t" + "max_skipped_exons" + "\t" + "min_skipped_bases" + "\t" + "max_skipped_bases");
//			writer.newLine();
//
//		} catch (Exception e) {
//			throw new RuntimeException("got error while printing txt", e);
//		}
//	}

	/**
	 * prints a tsv-file of everything in object Output
	 * 
	 * @throws Exception
	 */
	public void printOutTxt(FileWriter file, BufferedWriter writer) {

		try {
			
			writer.write(geneID + "\t" + geneName + "\t" + chr + "\t" + strand + "\t" + nprots + "\t" + ntrans + "\t");

			writer.write(sv.getX1() + ":" + sv.getX2() + "\t");

			String str = "";
			for (Region r : wt.regions) {
				str += ("|" + Integer.toString(r.getX1()) + ":" + Integer.toString(r.getX2()));
			}
			if (str.length() > 1) { // maybe could be removed. only important if RegionVector is empty. Shouldnt be
									// the case
				str = str.substring(1, str.length());
				writer.write(str + "\t");
			}

			String str1 = "";
			for (String id : wt_prots) {
				str1 += ("|" + id);
			}
			str1 = str1.substring(1, str1.length());
			writer.write(str1 + "\t");

			String str2 = "";
			for (String id : sv_prots) {
				str2 += ("|" + id);
			}
			str2 = str2.substring(1, str2.length());
			writer.write(str2 + "\t");

			writer.write(minSkippedExons + "\t" + maxSkippedExons + "\t" + minSkippedBases + "\t" + maxSkippedBases);
			writer.newLine();

		} catch (Exception e) {
			throw new RuntimeException("got error while printing output in txt.", e);
		}
	}

//	public void closeW() {
//		try {
//			writer.close();
//		} catch (Exception e) {
//			throw new RuntimeException("got error while closing output file.", e);
//		}
//	}

	public void setOutput(Gene gene, Region intron, RegionVector skippedExons) {
		this.geneID = gene.geneID;
		this.geneName = gene.geneName;
		this.chr = gene.geneChr;
		this.strand = gene.strand;
		this.nprots = gene.transcripts.size();
		this.ntrans = gene.nTrans();
		this.sv = intron;
		this.wt = skippedExons;
		this.minSkippedExons = 1;
		this.maxSkippedExons = 1;
		this.minSkippedBases = 1;
		this.maxSkippedBases = 1;
	}

}
