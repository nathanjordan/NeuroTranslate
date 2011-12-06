package unr.neurotranslate.util;

public class NCSParser {
	
	static {
		System.load("~/Downloads/workspace/NeuroTranslate/native/ncsparser.so");
		}
	
	public static native Object ParseInput( int node , String filename, int output );	
		
	}
