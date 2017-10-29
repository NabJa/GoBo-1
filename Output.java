package gobi;

public class Output {
	
	String geneID; //
	String geneSymbol; // 
	String chr; //chromosome
	String starnd; //
	int nprots; // number of annotated CDS in the gene
	int ntrans; // number of annotated transcripts in the gene
	Region sv; // the SV intron as start:end
	RegionVector wt; // the WT introns within the SV intron seperated by | as start:end
	String[] sv_prots; //IDs of the SV CDSs, seperated by |
	String[] wt_prots; //IDs of the WT CDSs, seperated by |
	int minSkippedExons; int maxSkippedExons; //min and max number of skipped Exons in any WT/SV pair
	int minSkippedBases; int maxSkippedBases; //min and max number of skipped bases in any WT/SV pair

	
	public Output(String geneID, String geneSymbol) {
		this.geneID = geneID;
		this.geneSymbol = geneSymbol;
	}
				

}
