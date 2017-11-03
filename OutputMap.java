package gobi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class OutputMap {

	FileWriter file;
	BufferedWriter writer;
	String outputDestination;
	
	public OutputMap(String outputDestination) {
		this.outputDestination = outputDestination;
		try {
			file = new FileWriter(outputDestination);
			writer = new BufferedWriter(file);
		} catch (Exception e) {
			throw new RuntimeException("got error while writing output from OutputMap.", e);
		}
	}
	
	HashMap<Region, Output> resultMap = new HashMap<Region, Output>();
	ArrayList<Output> resultList = new ArrayList<Output>();

	public boolean isInResults(Region r) {
		boolean answer = true;
		for (Output out : resultList) {
			if (out.sv.getX1() != r.getX1() || out.sv.getX2() != r.getX2()) {
				answer = false;
			}
		}
		return answer;
	}

	public void addIfnew(Region r, Output out) {
		if (!isInResults(r)) {
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
		for(Output out : resultList) {
			out.printOutTxt(outputDestination);
		}
	}
	
	public void closeAllout() {
		for(Output out : resultList) {
			out.closeW();
		}
	}
}
