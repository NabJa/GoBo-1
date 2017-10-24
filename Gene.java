package gobi;

import java.util.HashMap;

public class Gene {

	public String geneID;
	public int start;
	public int end;
	public String strand;
	public String type;
	public String name;
	public HashMap<String, Transcript> Components = new HashMap<String, Transcript>();

	// public Transcript transcript;
	// public ArrayList<RegionVector> Components = new ArrayList<RegionVector>();

	public Gene(String id, int start, int end, String strand, String type, String name) {
		this.geneID = id;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.type = type;
		this.name = name;
		//this.Components = null;
		//this.transcript = transcript;
}

	
	//	public void takeExon(RegionVector rv) {	
	//		transcript.insertExon(rv);
	//	}
	
	
		public void insertTranscript(Transcript trans) {
			Components.put(trans.getID(), trans);
		}
	
}