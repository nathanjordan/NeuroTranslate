package unr.neurotranslate.ncs;

public class TwoValue {
	
	public Double mean;
	
	public Double stdev;
	
	public void set( double x , double y ) {
		
		mean = x;
		
		stdev = y;
		
		}
	
	public TwoValue( ) {
		
		mean = 0.0;
		
		stdev = 0.0;
		
		}
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat( mean + "\t" + stdev + "\n" );
		
		return s;
		
		}
	
	}
