package gobi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GtfReadFast {

	public void readFast(String path, Output out) {

		BufferedReader reader = null;
		RegionVector currentRV = new RegionVector();
		Gene gene = new Gene();

		try {
			File file = new File(path);
			reader = new BufferedReader(new FileReader(file));

			String rline = "";
			// jumps over first annotation starting with "#"
			while (rline.startsWith("#")) {
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
				String geneID = line[8].substring(line[8].indexOf('\"') + 1, line[8].length() - 1);
				String geneName = line[9].substring(line[9].indexOf('\"') + 1, line[9].length() - 1);

				if (type.toLowerCase().equals("cds")) {
					Region cds = new Region(start, end);
					currentRV.addRegion(cds);
				} else if (type.toLowerCase().equals("transcript")) {
					RegionVector newRV = new RegionVector(line[9], start, end);
					gene.insertRV(newRV);
					currentRV = newRV;
				} else if (type.toLowerCase().equals("gene")) {
					gene.setGene(geneID, start, end, strand, chr, source, type, geneName);
					RVcomperator compr = new RVcomperator();
					compr.getSkippedExonFromGen(gene, out);
				}
			}
			RVcomperator compr = new RVcomperator();
			compr.getSkippedExonFromGen(gene, out);

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
