package unr.neurotranslate.ncsclasses;

public class Cell {
	
	public Locator l;
	
	public int nCmp;
	
	public List[] cmpNames;
	
	public char[][] labels;
	
	public int[] cmp;
	
	public double[] xpos;
	
	public double[] ypos;
	
	public double[] zpos;
	
	public double[] rpos;
	
	public int nConnect;
	
	public CompartmentConnect[] cnList;
	
	public CompartmentConnect[][] connect;
	
	public CompartmentConnect[][][] forwardConn;
	
	public CompartmentConnect[][][] reverseConn;
	
	public int[] nforward;
	
	public int[] nreverse;
	
	}
