package gobi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Gene {

	public String geneID;
	public int start;
	public int end;
	public String strand;
	public String source;
	public String type;
	public Map<RegionVector, String> transcripts = new HashMap<RegionVector, String>();

	public Set<Integer> wtStarts = new HashSet<Integer>();
	
	public void setGene(String id, int start, int end, String strand, String source, String type) {
		this.geneID = id;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.source = source;
		this.type = type;
	//	this.transcripts = new HashMap<RegionVector, String>();
	}

	/**
	 * inserts RegionVector in HashMap transcripts
	 * @param rv
	 */
	public void insertRV(RegionVector rv) {
			transcripts.put(rv, rv.getID());
		}
		
	public String getID() {
			return geneID;
		}
	
	
	
}