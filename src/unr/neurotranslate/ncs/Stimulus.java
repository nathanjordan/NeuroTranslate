package unr.neurotranslate.ncs;

public class Stimulus {
	
	public String type;
	
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
	
	public double[] timeStart;
	
	public double[] timeEnd;
	
	public double freqStart;
	
	public int seed;
	
	public double rate;
	
	public double tauNoise;
	
	public double correl;
	
	}
