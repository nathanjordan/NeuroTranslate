package unr.neurotranslate.util;

public class NCSParser {
	
	static {
		System.load("~/Downloads/workspace/NeuroTranslate/native/ncsparser.so");
		}
	
	public native Object ParseInput( int node , char[] filename, int output );
		
	}