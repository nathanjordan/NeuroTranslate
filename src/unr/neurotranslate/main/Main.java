/**
 * 
 * 
 * 
 */
package unr.neurotranslate.main;

import unr.neurotranslate.util.NCSParser;
/**
 * @author njordan
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
		
		//System.out.println("good!");
		
		Object returned;
		String filename = "ffdsa";
		//Always node 0 for now...output is 0 otherwise it will start printing stuff
		returned = NCSParser.ParseInput( 0 , filename , 0 );
		System.out.println(returned);
			
		
		}

}