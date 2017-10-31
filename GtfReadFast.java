package gobi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GtfReadFast {

	public void readFast(String path) {

		BufferedReader reader = null;
		RegionVector currentRV = new RegionVector();
		Gene gene = new Gene();
		
		try {
		    File file = new File(path);
		    reader = new BufferedReader(new FileReader(file));

		    String rline;
		    int i = 0;
			//jumps over first annotation starting with "#"
			while ((rline = reader.readLine()) != null && rline.startsWith("#")) {
					i++;
					continue;
			}
		    
		    while ((rline = reader.readLine()) != null) {
		    	
		    	String[] line = rline.split("(\t)|(;)");
		  		    	
		    	String chr = line[0];
		    	String source = line[1];
				String type = line[2];
				int start = Integer.parseInt(line[3]); // line[3] is always start and should be read as integer
				int end = Integer.parseInt(line[4]); // line[3] is always end and should be read as integer
				char strand = line[6].charAt(0);
				String geneID = line[8];
				String geneName = line[10];

				if (type.toLowerCase().equals("cds")) {
					Region cds = new Region(start, end);
					currentRV.addRegion(cds);
				} else if (type.toLowerCase().equals("transcript")) {
					RegionVector newRV = new RegionVector(line[9], start, end);
					gene.insertRV(newRV);
					currentRV = newRV;
				} else if (type.toLowerCase().equals("gene") || reader.readLine() != null) {
					//if(gene != null) {}
						RVcomperator compr = new RVcomperator();
						compr.getSkippedExonFromGen(gene);						
					
					gene.setGene(geneID, start, end, strand, chr, source, type, geneName); // creates new gene with new hashMap for transcripts
				}
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
	
	
}



