package unr.neurotranslate.ncsclasses;

public class Connect {
	
	public Locator l;
	
	public char[] fromColName;
	public char[] fromLayName;
	public char[] fromCmpName;
	// TODO kim added because there is no from cell name 
	public char[] fromCellName; 
	public char[] toColName;
	public char[] toLayName;
	public char[] toCellName;
	public char[] toCmpName;
	public char[] synName;
	
	public int fromCol;
	public int fromLay;
	public int fromCell;
	public int fromCmp;
	public int toCol;
	public int toLay;
	public int toCell;
	public int toCmp;
	public int synType;
	
	public double speed;
	public double prob;
	public double step;
	
	public double[] delay;
	public double[] recurrentProbability;
	
	public Connect recurrentConnection;
	
	public int disabled;
	
	}
