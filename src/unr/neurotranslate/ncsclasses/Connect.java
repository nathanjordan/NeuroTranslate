package unr.neurotranslate.ncsclasses;

public class Connect {
	
	public char[] fromColName;
	public char[] fromLayName;
	public char[] fromCmpName;
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
	
	public Number speed;
	public Number prob;
	public Number step;
	
	public Number[] delay;
	public Number[] recurrentProbability;
	
	public Connect recurrentConnection;
	
	public int disabled;
	
	}
