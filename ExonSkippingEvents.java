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

		System.out.println("Started reading");
		GtfReadFast gtfReader = new GtfReadFast();
		OutputMap outMap = new OutputMap(out);
		System.out.println("Started calc");
		gtfReader.readFast(inp, outMap);
		System.out.println("Started Writing");
		outMap.printOutput();
	}
}
