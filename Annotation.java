package GoBi1;

public class Annotation {

	public String chromosome;
	public String feature;
	public String type;
	public static String start;
	public static String end;
	public String score;
	public String strand;
	public String frame;
	public static String[] attribute;

	public Annotation(String chromosome, String feature, String type, String start, String end, String score,
			String strand, String frame, String[] attribute) {

		this.chromosome = chromosome;
		this.feature = feature;
		this.type = type;
		Annotation.start = start;
		Annotation.end = end;
		this.score = score;
		this.strand = strand;
		this.frame = frame;
		Annotation.attribute = attribute;

	}
	
	public String[] getPos(){
		String[] pos = {start, end};
		
		for(int i = 0; i < pos.length; i += 2) {
			System.out.println(pos[i] + ", " + pos[i+1]);
		}
		
		return pos;
	}
	
	public String[] printAll() {
		String[] gene = {attribute[1], feature, type, start, end};
		for(String e : gene) {
			System.out.println(e);
		}
		return gene;
	}
	
}
