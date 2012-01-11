package unr.neurotranslate.util;

import unr.neurotranslate.ncsclasses.Arrays;

/**
 * 
 * @author njordan
 * 
 * Loads the NCS shared object file into the Java environment and contains the method to parse an NCS file
 * 
 */
public class NCSParser {
	
	static {
		
		System.load("/home/njordan/Downloads/workspace/NeuroTranslate/ncsparser/ncsparser.so");
		
		}
	
	/**
	 * 
	 * @param node
	 * @param filename
	 * @param output
	 * @return An array object filled with parsed NCS data
	 * 
	 * Parses the NCS code via the JNI interface see the "ncsparser" folder
	 * 
	 */
	public static native Arrays ParseInput( int node , String filename, int output );	
		
	}
