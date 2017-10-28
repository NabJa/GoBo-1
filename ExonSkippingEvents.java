package gobi;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class ExonSkippingEvents {
 
	public static void main(String[] args) throws FileNotFoundException {
		
		GTFRead maus = new GTFRead("C:/Users/Anja/Desktop/GoBi/Mus_musculus.GRCm38.75.txt");
		maus.getGenes();
	
	}
}	

