package unr.neurotranslate.util;

public class NCSParser {
	
	static {
		System.load("/home/njordan/Downloads/workspace/NeuroTranslate/ncsparser/ncsparser.so");
		}
	
	public static native Object ParseInput( int node , String filename, int output );	
		
	}
