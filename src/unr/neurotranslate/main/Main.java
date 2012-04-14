/**
*
*
*
*/
package unr.neurotranslate.main;

import java.io.FileNotFoundException;
import unr.neurotranslate.model.FileController;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncsparser.NCSWriter;

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
	
	//test = converter.readNeuroML("/home/njordan/Downloads/Cerebellum.xml");
	
	//converter.neuromlToXml( test , "/home/njordan/Downloads/Cerebellum_out.xml");
	
	NCSData d = FileController.loadNCSFile( "samples/PrototypeNCS.in" );
	
	//NeuroMLConversionData n = FormatConverter.convertToNeuroML(d);
	
	//Neuroml neuroml = FileController.loadNeuroMLFile("/home/kimperry/Documents/PrototypeNeuroML.xml");
	
	//NCSConversionData ncs = FormatConverter.convertToNCS(neuroml);
	
	//Neuroml nm = (Neuroml) n.getData();
	
	//converter.neuromlToXml( nm , "/home/kimperry/Documents/NeuroMLTest.xml" );
	
	//NCSWriter.writeNCS(d, "/home//Desktop/ggg.in" );
	
	}

}