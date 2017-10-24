package gobi;

import java.util.ArrayList;
import java.util.HashMap;

public class Gene {

	public String geneID;
	public int start;
	public int end;
	public String strand;
	public String type;
	public String name;
	public HashMap<String, String[]> Components = new HashMap<String, String[]>();
	
	//public ArrayList<RegionVector> Components = new ArrayList<RegionVector>();

	public Gene(String id, int start, int end, String strand,
			String type, String name, ArrayList<RegionVector> Components) {
		this.geneID = id;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.type = type;
		this.name = name;
		this.Components = null;
	}
	
	public void putRV(RegionVector rv) {
		Components.put(rv.getID(), rv.getVector());
		
	}
	
	
	
}