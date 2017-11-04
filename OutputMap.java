package gobi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class OutputMap {

	FileWriter file;
	BufferedWriter writer;
	String outputDestination;
	
	public OutputMap(String outputDestination) {
		this.outputDestination = outputDestination;
		try {
			this.file = new FileWriter(outputDestination);
			this.writer = new BufferedWriter(file);
		} catch (Exception e) {
			throw new RuntimeException("got error while writing output from OutputMap.", e);
		}
	}
	
	HashMap<Region, Output> resultMap = new HashMap<Region, Output>();
	ArrayList<Output> resultList = new ArrayList<Output>();

	public boolean isNotInResults(Region r) {
		boolean answer = true;
		for (Output out : resultList) {
			//is intron already annotated?
			if (out.sv.getX1() == r.getX1() && out.sv.getX2() == r.getX2()) {
				answer = false;
			}
		}
		return answer;
	}

	public void addIfnew(Region r, Output out) {
		if (isNotInResults(r)) {
			resultList.add(out);
		}
	}

	public void addToResultIfNew(Region region, Output output) {
		if (!resultMap.containsKey(region)) {
			addToResultMap(region, output);
		}
	}

	public void addToResultMap(Region r, Output out) {
		resultMap.put(r, out);
	}

	public void printOutput() {
		try {
			writer.write("id" + "\t" + "symbol" + "\t" + "chr" + "\t" + "strand" + "\t" + "nprots" + "\t" + "ntrans"
					+ "\t" + "SV" + "\t" + "WT" + "\t" + "WT_prots" + "\t" + "SV_prots" + "\t" + "min_skipped_exons"
					+ "\t" + "max_skipped_exons" + "\t" + "min_skipped_bases" + "\t" + "max_skipped_bases");
			writer.newLine();

		} catch (Exception e) {
			throw new RuntimeException("got error while printing Headline!", e);
		}
		
		for(Output out : resultList) {
			out.printOutTxt(file, writer);
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void closeW() {
//		try {
//			writer.close();
//		} catch (Exception e) {
//			throw new RuntimeException("got error while closing output file.", e);
//		}
//	}
//	
//	/**
//	 * probably bullshit
//	 */
//	public void closeAllout() {
//		for(Output out : resultList) {
//			out.closeW();
//		}
//	}
}
