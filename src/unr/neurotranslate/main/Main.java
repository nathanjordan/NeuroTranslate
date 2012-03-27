/**
 * 
 * 
 * 
 */
package unr.neurotranslate.main;

import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;
import org.gnome.gtk.Window.DeleteEvent;
import org.morphml.neuroml.schema.Neuroml;
import unr.neurotranslate.conversion.FormatConverter;
import unr.neurotranslate.conversion.NCSConversionData;
import unr.neurotranslate.conversion.NeuroMLConversionData;
import unr.neurotranslate.model.FileController;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.neuromlparser.NeuroMLConverter;
import unr.neurotranslate.ui.*;

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
	
	public static void main(String[] args) throws Exception, FileNotFoundException {
		
		//Neuroml test;
		
		NeuroMLConverter converter = new NeuroMLConverter();

		//test = converter.readNeuroML("/home/njordan/Downloads/Cerebellum.xml");
		
		//converter.neuromlToXml( test , "/home/njordan/Downloads/Cerebellum_out.xml");

		//NCSData d = FileController.loadNCSFile( "/home/nitishn/Documents/PrototypeNCS.in" );

		//NeuroMLConversionData n = FormatConverter.convertToNeuroML(d);
		
		Neuroml neuroml = FileController.loadNeuroMLFile("/home/kimperry/Documents/PrototypeNeuroML.xml");
		
		NCSConversionData ncs = FormatConverter.convertToNCS(neuroml);
		
		//Neuroml nm = (Neuroml) n.getData();
		
		//converter.neuromlToXml( nm , "/home/kimperry/Documents/NeuroMLTest.xml" );
		
		}

}