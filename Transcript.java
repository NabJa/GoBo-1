package gobi;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Transcript {
	String id;
	int start;
	int end;
	Set<RegionVector> exons = new TreeSet<RegionVector>();
	Set<RegionVector> introns = new TreeSet<RegionVector>();
	Set<RegionVector> skippedExons = new HashSet<RegionVector>();

	Set<Integer> wt_starts = new TreeSet<Integer>();
	Set<Integer> wt_ends = new TreeSet<Integer>();

	public void insertCDS(RegionVector rv) {
		exons.add(rv);
	}

	/*
	 * sets values to the transcript. Its easy to make an empty transcript and fill it later with this method
	 */
	public void setTranscript(String id, int start, int end) {
		this.id = id;
		this.start = start;
		this.end = end;
	}

	/*
	 * gibt alle Anfänge von Introns brauch iterator um sinnvoll den ersten start
	 * auszulassen für spätere intron berechnung
	 */
	public void calculateStarts() {
		for (RegionVector rv : exons) {
			start = rv.getX2();
			wt_ends.add(start);
		}
	}

	/*
	 * calculates all ends of exons and saves them in wt_ends. Probably not needed.
	 */
	public void calculateEnds() {
		for (RegionVector rv : exons) {
			end = rv.getX1();
			wt_starts.add(end);
		}
	}

	public String getID() {
		return id;
	}

	public void calculateIntrons() {
		Iterator<RegionVector> iterator = exons.iterator();

		if (iterator.hasNext()) { // to move to second object
			iterator.next();
		}
		while (iterator.hasNext()) { // gets exon1
			RegionVector exon = iterator.next();
			Iterator<RegionVector> iterator2 = exons.iterator();

			while (iterator2.hasNext()) { // gets exon2 (=exon1++)
				RegionVector exon2 = iterator2.next();

				// save the end of exon1 and the start of exon2:
				RegionVector intron = new RegionVector(exon.getID() + "-intron", exon.getX2(), exon2.getX1());
				introns.add(intron);
			}
		}
	}

	
	public int getIntronLength() {
		
		firstIntron = introns.first();
		
	}
}
