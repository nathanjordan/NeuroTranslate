package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class SynLearning extends TypedElement {
	
	public Integer seed;
	
	public String learning;
	
	public String learningShape;
	
	public TwoValue negHebWindow = new TwoValue();
	
	public TwoValue posHebWindow = new TwoValue();
	
	public TwoValue posHebPeakDeltaUse = new TwoValue();
	
	public TwoValue negHebPeakDeltaUse = new TwoValue();
	
	public TwoValue posHebPeakTime = new TwoValue();
	
	public TwoValue negHebPeakTime = new TwoValue();
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("SYN_LEARNING\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty("SEED", seed, s);
		
		s = NCSWriter.writeProperty("LEARNING", learning, s);
		
		s = NCSWriter.writeProperty("LEARNING_SHAPE", learningShape, s);
		
		s = NCSWriter.writeProperty("NEG_HEB_WINDOW", negHebWindow, s);
		
		s = NCSWriter.writeProperty("POS_HEB_WINDOW", posHebWindow, s);
		
		s = NCSWriter.writeProperty("POS_HEB_PEAK_DELTA_USE", posHebPeakDeltaUse, s);
		
		s = NCSWriter.writeProperty("NEG_HEB_PEAK_DELTA_USE", negHebPeakDeltaUse, s);
		
		s = NCSWriter.writeProperty("POS_HEB_PEAK_TIME", posHebPeakTime, s);
		
		s = NCSWriter.writeProperty("NEG_HEB_PEAK_TIME", negHebPeakTime, s);
		
		s = s.concat("END_SYN_LEARNING\n\n");
		
		return s;
		
		}
	
	}
