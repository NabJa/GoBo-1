package gobi;

public class Region{

	public int x1;
	public int x2;
	public String regionID;

	Region(int x1, int x2) {
		this.x1 = x1;
		this.x2 = x2;
	}

	 Region(int x1, int x2, String id) {
	 this.x1 = x1;
	 this.x2 = x2;
	 this.regionID = id;
	 }

	public String getID() {
		return regionID;
	}

	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}

	public int getLength() {
		return x2 - x1;
	}

	public int hashCode() {
		return ((x1 * 104723) % 104729) + ((x2 * 104717) % 104711);
	}
	
	public String toString() {
		return "" + x1 + ":" + x2;
	}
}
