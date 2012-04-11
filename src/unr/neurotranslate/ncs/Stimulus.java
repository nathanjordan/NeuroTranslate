package unr.neurotranslate.ncs;

import java.util.ArrayList;

import unr.neurotranslate.ncsparser.NCSWriter;

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
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("STIMULUS\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty("MODE", mode, s);
		
		s = NCSWriter.writeProperty("PATTERN", pattern, s);
		
		s = NCSWriter.writeProperty("FILENAME", filename, s);
		
		s = NCSWriter.writeProperty("PORT", port, s);
		
		s = NCSWriter.writeProperty("TIME_INCREMENT", timeIncrement, s);
		
		s = NCSWriter.writeProperty("FREQ_COLS", freqCols, s);
		
		s = NCSWriter.writeProperty("CELLS_PER_FREQ", cellsPerFreq, s);
		
		s = NCSWriter.writeProperty("DYN_RANGE", dynRange, s);
		
		s = NCSWriter.writeProperty("TIMING", timing, s);
		
		s = NCSWriter.writeProperty("SAMESEED", sameSeed, s);
		
		s = NCSWriter.writeProperty("AMP_START", ampStart, s);
		
		s = NCSWriter.writeProperty("AMP_END", ampEnd, s);
		
		s = NCSWriter.writeProperty("PHASE", phase, s);
		
		s = NCSWriter.writeProperty("VERT_TRANS", vertTrans, s);
		
		s = NCSWriter.writeProperty("WIDTH", width, s);
		
		s = NCSWriter.writeProperty("FREQ_START", freqStart, s);
		
		if( timeStart.size() > 0 ) {
		
			s = s.concat("\tTIME_START");
			
			for( Double d : timeStart )
			
				s = s.concat( "\t" + d );
		
			s = s.concat("\n");
		
			}
		
		if( timeEnd.size() > 0 ) {
			
			s = s.concat("\tTIME_END");
			
			for( Double d : timeEnd )
			
				s = s.concat( "\t" + d );
		
			s = s.concat("\n");
		
			}
		
		s = NCSWriter.writeProperty("SEED", seed, s);
		
		s = NCSWriter.writeProperty("RATE", rate, s);
		
		s = NCSWriter.writeProperty("TAU_NOISE", tauNoise, s);
		
		s = NCSWriter.writeProperty("CORREL", correl, s);
		
		s = s.concat("END_STIMULUS\n\n");
		
		return s;
		
		}
	
	}
