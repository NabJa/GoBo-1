package gobi;

import java.util.HashSet;
import java.util.Set;

public class Transcript {
	String id;
	int start;
	int end;
	Set<RegionVector> exons = new HashSet<RegionVector>();
	Set<RegionVector> introns = new HashSet<RegionVector>();
	Set<Integer> wt_starts = new HashSet<Integer>();
	Set<Integer> wt_ends = new HashSet<Integer>();

	public Transcript(String id, int start, int end) {
		this.id = id;
		this.start = start;
		this.end = end;
	}
		
	public void insertExon(RegionVector rv) {
		exons.add(rv);
	}
	
	
	//gibt alle Anfänge von Introns
	//brauch iterator um sinnvoll den ersten start auszulassen für spätere 
	//intron berechnung
	public void calculateStarts() { 
		for(int i = 1; i < exons.size(); i++) {
			start = exons[i].getX2();
			wt_starts.add(start);
		}
	}
	
	public void calculateEnds() {
		for(RegionVector rv : exons) {
			end = rv.getX1();
			wt_starts.add(start);
		}
	}
	
	
}
