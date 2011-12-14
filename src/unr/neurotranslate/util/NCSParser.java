package unr.neurotranslate.util;

import unr.neurotranslate.ncsclasses.Arrays;

public class NCSParser {
	
	static {
		System.load("/home/njordan/Downloads/workspace/NeuroTranslate/ncsparser/ncsparser.so");
		}
	
	public static native Arrays ParseInput( int node , String filename, int output );	
		
	}
