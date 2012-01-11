/**
 * 
 * 
 * 
 */
package unr.neurotranslate.main;

import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncsclasses.Arrays;
import unr.neurotranslate.util.NCSParser;
import unr.neurotranslate.util.NeuroMLConverter;

/**
 * @author njordan
 * 
 * Main Entry Point of the program
 * 
 */
public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		
		Neuroml test;
		
		NeuroMLConverter converter = new NeuroMLConverter();
		
		test = converter.readNeuroML("/home/njordan/Downloads/Cerebellum.xml");
		
		converter.neuromlToXml( test , "/home/njordan/Downloads/Cerebellum_out.xml");
		
		@SuppressWarnings("unused")
		Arrays returned;
		
		String filename = "/home/njordan/Downloads/PrototypeNCS.in";
		
		//Always node 0 for now...output is 0 otherwise it will start printing stuff
		returned = NCSParser.ParseInput( 0 , filename , 0 );
		
		System.out.println("Good!");
		
		}

}