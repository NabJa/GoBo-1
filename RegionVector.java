package gobi;

public class RegionVector {
	public String id;
	public int x1;
	public int x2;

	public RegionVector(String id, int x1, int x2) {
		this.id = id;
		this.x1 = x1;
		this.x2 = x2;
	}
	
	
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public String getID() {
		return id;
	}
	
	public String[] getVector() {
		String x11 = String.valueOf(x1);
		String x22 = String.valueOf(x2);
		String[] vector = {id, x11, x22}; 
		return vector;
	}	
	
	
	public int hashCode() {
		return ((x1 * 104723) % 104729)
				+ ((x2 * 104717) % 104711);
	}
}