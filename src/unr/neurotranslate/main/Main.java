/**
 * 
 * 
 * 
 */
package unr.neurotranslate.main;

import org.morphml.neuroml.schema.Neuroml;
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
		
		
		}

}