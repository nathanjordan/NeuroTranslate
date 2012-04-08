package unr.neurotranslate.ncs;

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
	
	}
