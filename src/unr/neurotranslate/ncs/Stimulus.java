package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Stimulus extends TypedElement {
	
	public String mode;
	
	public String pattern;
	
	public String filename;
	
	public Integer port;
	
	public Double timeIncrement;
	
	public Integer freqCols;
	
	public Integer cellsPerFreq;
	
	public TwoValue dynRange = new TwoValue();
	
	public String timing;
	
	public Integer sameSeed;
	
	public Double ampStart;
	
	public Double ampEnd;
	
	public Double phase;
	
	public Double vertTrans;
	
	public Double width;
	
	public ArrayList<Double> timeStart;
	
	public ArrayList<Double> timeEnd;
	
	public Double freqStart;
	
	public Integer seed;
	
	public Double rate;
	
	public Double tauNoise;
	
	public Double correl;
	
	public Stimulus() {
		
		timeEnd = new ArrayList<Double>();
		
		timeStart = new ArrayList<Double>();
		
		}
	
	}
