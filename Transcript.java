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

		
	public void insertExon(RegionVector rv) {
		exons.add(rv);
	}
	
	public void setTranscript(String id, int start, int end) {
		this.id = id;
		this.start = start;
		this.end = end;
	}
	
	
	/*
	*gibt alle Anfänge von Introns
	*brauch iterator um sinnvoll den ersten start auszulassen für spätere 
	*intron berechnung
	*/
	public void calculateStarts() { 
		for(RegionVector rv : exons) {
			start = rv.getX2();
			wt_ends.add(start);
		}
	}
	
	public void calculateEnds() {
		for(RegionVector rv : exons) {
			end = rv.getX1();
			wt_starts.add(end);
		}
	}
	
	public String getID() {
		return id;
	}
	
	
}
