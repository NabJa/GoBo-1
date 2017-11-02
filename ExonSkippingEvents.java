package gobi;

import java.util.ArrayList;

public class ExonSkippingEvents {

	public static void main(String[] args) throws Exception {

		 String input = "C:/Users/Anja/Desktop/GoBi/endTest.input";
		 String out = "C:/Users/Anja/Desktop/testOutput.txt";
		
		 GtfReadFast maus = new GtfReadFast();
		 Output output = new Output(out);
		 output.printHeader();
		 maus.readFast(input, output);
		 output.closeW();

		// Test calculation of exons
//		Gene gene = new Gene();
//		gene.setGene("ENSG83493", 30, 300, '-', "6", "JabLab", "gene", "HawkinGEne");
//		// HashMap<String, RegionVector> transcripts = new HashMap<String,
//		// RegionVector>();
//
//		RegionVector rv1 = new RegionVector("rv1", 1, 3);
//		RegionVector rv2 = new RegionVector("rv2", 6, 12);
//		RegionVector rv3 = new RegionVector("rv3", 11, 16);
//		RegionVector rv4 = new RegionVector("rv4", 15, 19);
//		RegionVector rv5 = new RegionVector("rv5", 20, 30);
//
//		Region r1 = new Region(10, 20);
//		Region r2 = new Region(30, 40);
//		Region r3 = new Region(50, 60);
//		Region r4 = new Region(70, 80);
//		rv1.addRegion(r1);
//		rv1.addRegion(r2);
//		rv1.addRegion(r3);
//		rv1.addRegion(r4);
//		gene.insertRV(rv1);
//
//		Region r5 = new Region(10, 20);
//		Region r6 = new Region(50, 60);
//		Region r7 = new Region(70, 80);
//		rv2.addRegion(r5);
//		rv2.addRegion(r6);
//		rv2.addRegion(r7);
//		gene.insertRV(rv2);
//
//		// Region r8 = new Region(1, 2);
//		// Region r9 = new Region(3, 4);
//		 Region r92 = new Region(10, 20);
//		 Region r91 = new Region(70, 80);
//		// rv3.addRegion(r8);
//		// rv3.addRegion(r9);
//		 rv3.addRegion(r92);
//		 rv3.addRegion(r91);
//		 gene.insertRV(rv3);
//		//
//		// Region r10 = new Region(1, 2);
//		// Region r11 = new Region(7, 8);
//		// rv4.addRegion(r10);
//		// rv4.addRegion(r11);
//		// gene.insertRV(rv4);
//		//
//		// Region r12 = new Region(2, 4);
//		// Region r13 = new Region(7, 8);
//		// rv5.addRegion(r12);
//		// rv5.addRegion(r13);
//		// gene.insertRV(rv5);
//
//		// transcripts.put(rv1.id, rv1);
//		// transcripts.put(rv2.id, rv2);
//		// transcripts.put(rv3.id, rv3);
//		// transcripts.put(rv4.id, rv4);
//		// transcripts.put(rv5.id, rv5);
//
//		RVcomperator compr = new RVcomperator();
//
//		Output out = new Output("C:/Users/Anja/Desktop/testOutput.txt");
//		out.printHeader();
//		compr.getSkippedExonFromGen(gene, out);
//		out.closeW();
		
		
//		HashSet<String> starts = compr.getOverlappingIntrons(40, 70, gene.transcripts);
//		System.out.println(starts.contains("rv1"));
//		System.out.println(starts.contains("rv2"));
	}
}
