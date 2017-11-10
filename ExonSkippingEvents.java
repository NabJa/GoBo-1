package gobi;

import java.util.HashSet;
import java.util.Set;

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

		CDSReader cdsReader = new CDSReader();
		cdsReader.readCDS(inp);

		OutputMap outMap = new OutputMap(out);
		RVcomperator compr = new RVcomperator();
		
		for(Gene gene : cdsReader.genes.values()) {
			compr.getSkippedExonFromGen(gene, outMap);
		}
		outMap.printOutput1();
	}
}
