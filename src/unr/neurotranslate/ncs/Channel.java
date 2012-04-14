package unr.neurotranslate.ncs;

public class Channel extends TypedElement {
	
	public String channelType;
	
	public TwoValue mInitial = new TwoValue();
	
	public TwoValue mPower = new TwoValue();
	
	public TwoValue reversalPotential = new TwoValue();
	
	public TwoValue unitaryG = new TwoValue();
	
	public TwoValue strength = new TwoValue();
	
	//Km channel
	
	public TwoValue eHalfMinM = new TwoValue();
	
	public Float[] slopeFactorM;
	
	public TwoValue tauScaleFactorM = new TwoValue();
	
	public Integer seed;
	
	//Kahp channel
	
	public TwoValue caScaleFactor = new TwoValue();
	
	public TwoValue caExpFactor = new TwoValue();
	
	public TwoValue caHalfMin = new TwoValue();
	
	public TwoValue caTauScaleFactor = new TwoValue();
	
	//Ka channel
	
	public TwoValue hInitial = new TwoValue();
	
	public TwoValue hPower = new TwoValue();
	
	public TwoValue eHalfMinMKa = new TwoValue();
	
	public TwoValue eHalfMinH = new TwoValue();
	
	public TwoValue slopeFactorMKa = new TwoValue();
	
	public TwoValue slopeFactorH = new TwoValue();
	
	public Float[] vTauValueM;
	
	public Float[] vTauVoltageM;
	
	public Float[] vTauValueH;
	
	public Float[] vTauVoltageH;
	
	public Channel( String chType ) {
		
		channelType = chType;
		
		}
	
	public Channel( ) {
			
			}
	
	}
