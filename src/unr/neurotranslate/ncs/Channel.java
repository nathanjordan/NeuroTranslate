package unr.neurotranslate.ncs;

public class Channel {
	
	String type;
	
	MeanStdev mInitial;
	
	MeanStdev mPower;
	
	MeanStdev reversalPotential;
	
	MeanStdev unitaryG;
	
	MeanStdev strength;
	
	//Km channel
	
	MeanStdev eHalfMinM;
	
	float[] slopeFactorM;
	
	MeanStdev tauScaleFactorM;
	
	int seed;
	
	//Kahp channel
	
	MeanStdev caScaleFactor;
	
	MeanStdev caExpFactor;
	
	MeanStdev caHalfMin;
	
	MeanStdev caTauScaleFactor;
	
	//Ka channel
	
	MeanStdev hInitial;
	
	MeanStdev hPower;
	
	MeanStdev eHalfMinMKa;
	
	MeanStdev eHalfMinH;
	
	MeanStdev slopeFactorMKa;
	
	MeanStdev slopeFactorH;
	
	float[] vTauValueM;
	
	float[] vTauVoltageM;
	
	float[] vTauValueH;
	
	float[] vTauVoltageH;
	
	}
