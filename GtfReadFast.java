package gobi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GtfReadFast {

	public void readFast(String path, OutputMap outMap) {

		BufferedReader reader = null;
		RegionVector currentRV = new RegionVector();
		Gene gene = new Gene();

		
		try {
			File file = new File(path);
			reader = new BufferedReader(new FileReader(file));
			int i = 0;
			
			String rline = "";
			// jumps over first annotation starting with "#"
			// while ((rline = reader.readLine()) != null && rline.startsWith("#")) {
			// //.indexOf('#') == 0
			// continue;
			// }

			while ((rline = reader.readLine()) != null) {

				i++;
				if(i > 15000) {
					break;
				}

				if (rline.indexOf('#') != 0) {

					String[] line = rline.split("(\t)|(;)");
					String chr = line[0];
					String source = line[1];
					String type = line[2];
					int start = Integer.parseInt(line[3]); // line[3] is always start and should be read as integer
					int end = Integer.parseInt(line[4]); // line[3] is always end and should be read as integer
					String strand = line[6];
//					String geneID = line[8].substring(line[8].indexOf('\"') + 1, line[8].length() - 1);
//					String geneName = line[9].substring(line[9].indexOf('\"') + 1, line[9].length() - 1);

					if (type.toLowerCase().equals("cds")) {
						Region cds = new Region(start, end);

						// immer letzter eintrag is proteinID aber nicht immer index=15
						// String proteinID = line[line.length - 1].substring(line[line.length -
						// 1].indexOf('\"') + 1,
						// line[line.length - 1].length() - 1);
						cds.regionID = proteinIDSearch(line);
						currentRV.addRegion(cds);
					} else if (type.toLowerCase().equals("transcript")) {
						String  transID = transcriptIDSearch(line);
						RegionVector newRV = new RegionVector(transID, start, end);
						gene.insertRV(newRV);
						currentRV = newRV;
						
					} else if (type.toLowerCase().equals("gene")) {	
						String geneID = geneIDSearch(line);
						String geneName = geneNameSearch(line);
						RVcomperator compr = new RVcomperator();
						compr.getSkippedExonFromGen(gene, outMap);
						gene.setGene(geneID, start, end, strand, chr, source, type, geneName);
						i = 0;
					}
				}
			}

			RVcomperator compr = new RVcomperator();
			compr.getSkippedExonFromGen(gene, outMap);
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

	public String proteinIDSearch(String[] line) {
		String targetID = "";
		for (int i = 0; i < line.length; i++) {
			if (line[i].indexOf('p') == 1 && line[i].indexOf('d') == 10) {
				targetID = line[i].substring(line[i].indexOf('\"') + 1, line[i].length() - 1);
				break;
			}
		}
		return targetID;
	}

	
	//viel zu langsam diese loops! fixen!!
	public String transcriptIDSearch(String[] line) {
		String targetID = "";
		for (int i = 0; i < line.length; i++) {
			if (line[i].indexOf('t') == 1 && line[i].indexOf('d') == 13) {
				targetID = line[i].substring(line[i].indexOf('\"') + 1, line[i].length() - 1);
				break;
			}
		}
		return targetID;
	}

	public String geneNameSearch(String[] line) {
		String targetID = "";
		for (int i = 0; i < line.length; i++) {
			if (line[i].indexOf('g') == 1 && line[i].indexOf('m') == 8) {
				targetID = line[i].substring(line[i].indexOf('\"') + 1, line[i].length() - 1);
				break;
			}
		}
		return targetID;
	}

	public String geneIDSearch(String[] line) {
		String targetID = "";
		for (int i = 0; i < line.length; i++) {
			if (line[i].indexOf('g') == 0 && line[i].indexOf('d') == 6) {
				targetID = line[i].substring(line[i].indexOf('\"') + 1, line[i].length() - 1);
				break;
			}
		}
		return targetID;
	}
}
