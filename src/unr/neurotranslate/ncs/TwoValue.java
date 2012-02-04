package unr.neurotranslate.ncs;

public class TwoValue {
	
	public double mean;
	
	public double stdev;
	
	public void set( double x , double y ) {
		
		mean = x;
		
		stdev = y;
		
		}
	
	public TwoValue( ) {
		
		mean = 0.0;
		
		stdev = 0.0;
		
		}
	
	}
