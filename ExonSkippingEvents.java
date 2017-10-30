package gobi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


public class ExonSkippingEvents {
 		
	public static void main(String[] args) throws Exception {
		
//		GTFRead maus = new GTFRead("C:/Users/Anja/Desktop/GoBi/inverseTester.txt");
//		maus.getGenes();

		// Test calculation of exons
		HashMap<String, RegionVector> transcripts = new HashMap<String, RegionVector>();
		
		RegionVector rv1 = new RegionVector("rv1", 1, 3);
		RegionVector rv2 = new RegionVector("rv2", 6, 12);
		RegionVector rv3 = new RegionVector("rv3", 11, 16);
		RegionVector rv4 = new RegionVector("rv4", 15, 19);
		RegionVector rv5 = new RegionVector("rv5", 20, 30);
		
		Region r1 = new Region(35, 45);
		Region r2 = new Region(67, 82);
		Region r3 = new Region(90, 95);
		Region r4 = new Region(101, 121);
		rv1.addRegion(r1);
		rv1.addRegion(r2);
		rv1.addRegion(r3);
		rv1.addRegion(r4);
		
		Region r5 = new Region(35, 45);
		Region r6 = new Region(67, 95);
		Region r7 = new Region(90, 121);
		rv2.addRegion(r5);
		rv2.addRegion(r6);
		rv2.addRegion(r7);
		
		Region r8 = new Region(36, 45);
		rv3.addRegion(r8);
		Region r9 = new Region(67, 82);
		rv3.addRegion(r9);
		Region r92 = new Region(91, 95);
		rv3.addRegion(r92);
		Region r91 = new Region(100, 121);
		rv3.addRegion(r91);
			
		
		Region r10 = new Region(67, 82);
		Region r11 = new Region(101, 121);
		rv4.addRegion(r10);
		rv4.addRegion(r11);
		
		Region r12 = new Region(35, 45);
		Region r13 = new Region(67, 82);
		rv5.addRegion(r12);
		rv5.addRegion(r13);

		transcripts.put(rv1.id, rv1);
		transcripts.put(rv2.id, rv2);
		transcripts.put(rv3.id, rv3);
		transcripts.put(rv4.id, rv4);
		transcripts.put(rv5.id, rv5);
		
		RVcomperator compr = new RVcomperator();
		
		Region intron = new Region(67, 95);

		compr.getSkippedExons(intron, "rv9", "ID: whatever", transcripts);
		
		
		
//		TEST SUBTRACT METHOD FROM RVcomperator
//		System.out.println(intron.getX1() + " " + intron.getX2());
//		
//		System.out.println(".........");
//		
//		compr.printRV(rv1);
//		
//		System.out.println(".........");
//
//		RegionVector testRV = compr.subtract(intron, rv1);
//		compr.printRV(testRV);
		
		
		
		/*  Test the output writer
		String[] svprots = {"ENSP00000349276","ENSP00000441052","ENSP00000265368","ENSP00000356224"};
		String[] wtprots = {"ENSP00000349276","ENSP00000265368","ENSP00000356224"};

		
		Output out = new Output("ENSG00000131018", "SYNE1");
		out.chr="6";
		out.strand = "-";
		out.sv = new Region(123, 345);
		out.wt = new RegionVector();
		out.sv_prots = svprots;
		out.wt_prots = wtprots;
		out.minSkippedBases = 1;
		out.maxSkippedBases = 123;
		out.minSkippedExons = 3;
		out.maxSkippedExons = 15;
		out.nprots = 1233;
		out.ntrans = 3434;
		
		out.printHeader();
		int i = 1000;
		while(i > 0) {
			out.printOutTxt();
			i--;
		}
		out.closeW();
	*/
		
		
		
		
/*		
		
		HashSet<String> starts = new HashSet<String>();
		HashSet<String> ends = new HashSet<String>();
		HashSet<String> sameIntron = new HashSet<String>();

		RVcomperator compr = new RVcomperator();

		starts = compr.getStarts(20, transcripts);	
		ends = compr.getEnds(40, transcripts);
		
		sameIntron = compr.getSameIntron(starts, ends);
		
//		for (String e : sameIntron) {
//			System.out.println(e);
//		}
		
		RegionVector skipped = compr.subtract(r8, rv4);
		
		for(Region r : skipped.regions) {
			System.out.println(r.getX1() + " " + r.getX2());
		}
*/
	}
}	

