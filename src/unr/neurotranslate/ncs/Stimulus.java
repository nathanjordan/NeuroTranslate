package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Stimulus extends TypedElement {
	
	public String mode;
	
	public String pattern;
	
	public String filename;
	
	public int port;
	
	public double timeIncrement;
	
	public int freqCols;
	
	public int cellsPerFreq;
	
	public TwoValue dynRange = new TwoValue();
	
	public String timing;
	
	public int sameSeed;
	
	public double ampStart;
	
	public double ampEnd;
	
	public double phase;
	
	public double vertTrans;
	
	public double width;
	
	public ArrayList<Double> timeStart;
	
	public ArrayList<Double> timeEnd;
	
	public double freqStart;
	
	public int seed;
	
	public double rate;
	
	public double tauNoise;
	
	public double correl;
	
	public Stimulus() {
		
		timeEnd = new ArrayList<Double>();
		
		timeStart = new ArrayList<Double>();
		
		}
	
	}
