/**
 * 
 * 
 * 
 */
package unr.neurotranslate.main;

import org.gnome.gtk.Builder;
import org.gnome.gtk.Gtk;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.conversion.FormatConverter;
import unr.neurotranslate.conversion.NeuroMLConversionData;
import unr.neurotranslate.model.FileController;
import unr.neurotranslate.ncs.NCSData;
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
		
		NeuroMLConverter converter = new NeuroMLConverter();
		
		//test = converter.readNeuroML("/home/njordan/Downloads/Cerebellum.xml");
		
		//converter.neuromlToXml( test , "/home/njordan/Downloads/Cerebellum_out.xml");

		NCSData d = FileController.loadNCSFile( "/home/kimperry/Documents/PrototypeNCS.in" );
		
		NeuroMLConversionData n = FormatConverter.convertToNeuroML(d);
		
		Neuroml nm = (Neuroml) n.getData();
		
		converter.neuromlToXml( nm , "/home/kperry/workspace/NeuroTranslate/samples/out.xml" );
		
		}

}