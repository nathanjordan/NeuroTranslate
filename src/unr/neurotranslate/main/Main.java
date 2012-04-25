/**
*
*
*
*/
package unr.neurotranslate.main;

import java.io.FileNotFoundException;

import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.conversion.FormatConverter;
import unr.neurotranslate.conversion.NCSToNeuroml;
import unr.neurotranslate.conversion.NeuroMLConversionData;
import unr.neurotranslate.model.FileController;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncsparser.NCSWriter;
import unr.neurotranslate.neuromlparser.NeuroMLConverter;
import unr.neurotranslate.ui.controller.UIControllerNCS;

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

	//NeuroMLConverter converter = new NeuroMLConverter();


	//test = converter.readNeuroML("samples/PrototypeNeuroml.xml");

	//NCSData d = FileController.loadNCSFile( "samples/PrototypeNCS.in" );


	//UIControllerNCS u = new UIControllerNCS();
	
	//NCSData d1 = (NCSData) FormatConverter.convertToNCS(test).getData();

	//NCSWriter.writeNCS(d1, "/home/kimperry/Desktop/conversion.in" );
	
	//System.out.println(u.getColumnShells().size());
	
	NCSData d2 = FileController.loadNCSFile( "samples/PrototypeNCS.in" );

	//NeuroMLConversionData n = FormatConverter.convertToNeuroML(d2);
	
	String s = "5";
	
	//converter.neuromlToXml( (Neuroml) n.getData() , "/home/kimperry/Desktop/conversion.xml" );

	}

}