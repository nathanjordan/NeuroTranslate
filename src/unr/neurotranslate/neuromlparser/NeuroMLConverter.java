package unr.neurotranslate.neuromlparser;

import java.io.*;

import javax.xml.bind.*;
import javax.xml.namespace.*;

import org.morphml.channelml.schema.ChannelmlType;
import org.morphml.morphml.schema.Morphml;
import org.morphml.networkml.schema.Networkml;
import org.morphml.neuroml.schema.Neuroml;

/**
 * 
 * @author njordan
 * 
 * Handles all the conversions between the code and NeuroML
 * 
 */
public class NeuroMLConverter {
	
	/** Instance of the jaxb framework **/
	protected JAXBContext jaxb;
	
	/** does the converting **/
	protected Marshaller marshaller;
	
	protected Unmarshaller unmarshaller;	
	/**
	 * @throws Exception
	 * @author njordan
	 * 
	 * Constructor for the NeuroML converter, instantiates a new jaxb marshaller, and creates the tools
	 * neccisary to marshall and unmarshall biology into NeuroML files
	 */
	public NeuroMLConverter() throws Exception {
		
		jaxb = JAXBContext.newInstance("org.morphml.biophysics.schema:org.morphml.channelml.schema:org.morphml.metadata.schema:org.morphml.morphml.schema:org.morphml.networkml.schema:org.morphml.neuroml.schema");
		
		marshaller = jaxb.createMarshaller();		
		
		marshaller.setProperty( "com.sun.xml.internal.bind.namespacePrefixMapper" , new NeuroMLNamespacePrefixMapper() );
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		unmarshaller = jaxb.createUnmarshaller();
		
		}
	
	/**
	 * 
	 * @param xmlFile
	 * @return
	 * @throws Exception
	 * 
	 * Reads a NeuroML file and converts it to a Java object
	 */
	public Neuroml readNeuroML(String xmlFile) throws Exception {
		
		File f = new File(xmlFile);
		
		if (!f.exists()) throw new FileNotFoundException(f.getAbsolutePath());
		
		return (Neuroml) unmarshaller.unmarshal(f);
		
		}
	
	/**
	 * 
	 * @param xmlFile
	 * @return
	 * @throws Exception
	 * 
	 * Converts an Morphology (Level 1) into a Java object
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Morphml xmlToMorphology(String xmlFile) throws Exception {
		
		File f = new File(xmlFile);
		
		if (!f.exists()) 
			throw new FileNotFoundException(f.getAbsolutePath());
		
		JAXBElement<Morphml> jbe = (JAXBElement<Morphml>) unmarshaller.unmarshal(f);
		
		return jbe.getValue();	
		
		}
	
	/**
	 * 
	 * @param xmlfile
	 * @return
	 * @throws Exception
	 * 
	 * Converts ChannelML(Level 2) into a Java object
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ChannelmlType xmlToChannel(String xmlfile) throws Exception {
		
		File f = new File(xmlfile);
		
		if (!f.exists()) throw new FileNotFoundException(f.getAbsolutePath());
		
		JAXBElement<ChannelmlType> jbe = (JAXBElement<ChannelmlType>) unmarshaller.unmarshal(f);
		
		return jbe.getValue();
	
		}
	
	/**
	 * 
	 * @param xmlfile
	 * @return
	 * @throws Exception
	 * 
	 * Converts NetworkML (Level 3) to a Java object
	 * 
	 */
	public Networkml xmlToNetwork(String xmlfile) throws Exception {
		
		File f = new File(xmlfile);
		
		if (!f.exists()) 
			throw new FileNotFoundException(f.getAbsolutePath());
		
		//deprecated
		
		/*JAXBElement<Networkml> jbe =
			(JAXBElement<Networkml>) unmarshaller.unmarshal(f);
		
		return jbe.getValue();*/
		
		return (Networkml) unmarshaller.unmarshal(f);
		
		}
	
	/**
	 * 
	 * @param morph
	 * @param filename
	 * @throws Exception
	 * 
	 * Converts a cell Morphology (Level 1) object to XML
	 * 
	 */
	public void morphologyToXml(Morphml morph, String filename) throws Exception {
		
		JAXBElement<Morphml> jbm =
			new JAXBElement<Morphml>(new QName("morphml"),
					                    org.morphml.morphml.schema.Morphml.class,
					                    morph);
		File f = new File(filename);
		
		FileOutputStream fos = new FileOutputStream(f);
				
		marshaller.marshal(jbm, fos);
		
		fos.close();
		
		}
	/**
	 * 
	 * @param chan
	 * @param filename
	 * @throws Exception
	 * 
	 * Converts a cell Channel (Level 2) object to XML
	 * 
	 */
	public void channelToXml(ChannelmlType chan, String filename) throws Exception {
		
		JAXBElement<ChannelmlType> jbc = new JAXBElement<ChannelmlType>(
				new QName("channelml"),
				org.morphml.channelml.schema.ChannelmlType.class,
				chan);
		
		File f = new File(filename);
		
		FileOutputStream fos = new FileOutputStream(f);
				
		marshaller.marshal(jbc, fos);
		
		fos.close();
	
		}
	
	/**
	 * 
	 * @param l3
	 * @param filename
	 * @throws Exception
	 * 
	 * Converts a cell Network (Level 3) object to XML
	 * 
	 */
	public void neuromlToXml(Neuroml l3, String filename) throws Exception {
		
		JAXBElement<Neuroml> jbc = new JAXBElement<Neuroml>(new QName("neuroml"), org.morphml.neuroml.schema.Neuroml.class, l3);
		
		File f = new File(filename);
		
		FileOutputStream fos = new FileOutputStream(f);
				
		marshaller.marshal(jbc, fos);
		
		fos.close();
		
		}
	
	}
