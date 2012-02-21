/**
 * 
 * 
 * 
 */
package unr.neurotranslate.main;

import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncsparser.Parser;
import unr.neurotranslate.ncsparser.ParserValInterpolater;
import unr.neurotranslate.neuromlparser.NeuroMLConverter;

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
		
		//Neuroml test;
		
		//NeuroMLConverter converter = new NeuroMLConverter();
		
		//test = converter.readNeuroML("/home/njordan/Downloads/Cerebellum.xml");
		
		//converter.neuromlToXml( test , "/home/njordan/Downloads/Cerebellum_out.xml");
		
		Parser p = new Parser("/home/njordan/Downloads/PrototypeNCS.in");
		
		p.run();
		
		ParserValInterpolater g = new ParserValInterpolater(p);
		
		System.out.println("weeee");
		
		}

}