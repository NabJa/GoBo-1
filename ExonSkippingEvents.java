package gobi;

import java.util.ArrayList;

public class ExonSkippingEvents {

	public static String inp;
	public static String out;

	public static void main(String[] args) throws Exception {

		String noInp = "Pls enter -o fllowed by output path and -gtf followed by input path !!";

		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "-gtf":
				inp = args[i + 1];
				i++;
				break;
			case "-o":
				out = args[i + 1];
				i++;
				break;
			default:
				System.out.println(noInp);
			}
		}

		// GtfReadFast gtfReader = new GtfReadFast();
		// gtfReader.readFast(inp, outMap);

		CDSReader cdsReader = new CDSReader();
		cdsReader.readCDS(inp);
		RVcomperator compr = new RVcomperator();
		OutputMap outMap = new OutputMap(out);
		for(Gene gene : cdsReader.genes.values()) {
			compr.getSkippedExonFromGen(gene, outMap);
		}
		outMap.printOutput();

		// String rline = "IV protein_coding CDS 3762 3833 . + 0 gene_id \"YDL247W-A\";
		// transcript_id \"YDL247W-A\"; exon_number \"1\"; gene_source \"ensembl\";
		// gene_biotype \"protein_coding\"; transcript_name \"YDL247W-A\";
		// transcript_source \"ensembl\"; protein_id \"YDL247W-A\";";
		// int firstTab = rline.indexOf('\t');
		// int secondTab = rline.indexOf('\t', firstTab+1);
		//
		// boolean bol = rline.substring(secondTab + 1, secondTab +
		// 4).toLowerCase().equals("cds");
		//
		// System.out.println(rline.substring(secondTab+1, secondTab+4).toLowerCase());
		// System.out.println(bol);

	}
}
