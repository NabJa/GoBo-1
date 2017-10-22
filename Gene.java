package GoBi1;

public class Gene {
	
	public static String id;
	public static int start;
	public static int end;
	public String strand;
	public String type;
	public String name;

	public Gene(String id, int start, int end, String strand, String type, String name)
	{
		Gene.id = id;
		Gene.start = start;
		Gene.end = end;
		this.strand = strand;
		this.type = type;
		this.name = name;
	}
	
	public static String getID()
	{
		return id;
	}
	
	public int getStart()
	{
		return start;
	}
	
	public int getEnd()
	{
		return end;
	}
	
	public String getStrand()
	{
		return strand;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public static int[] getLoc()
	{
		int[] regionVecotr = {start, end};
		return regionVecotr;
		
	}
	
}
