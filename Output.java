package GoBi1;

import java.io.IOException;

public class Output extends GFFimport {

	public Output(String chromosome, String feature, String type, String start, String end, String score, String strand,
			String frame, String attribute) {
		super(chromosome, feature, type, start, end, score, strand, frame, attribute);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\Anja\\Desktop\\GoBi\\gtfRawEasy.txt";
		
		System.out.println(giveAll(path));
	}		
}
