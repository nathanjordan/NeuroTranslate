package unr.neurotranslate.util;

import java.io.*;

import javax.xml.bind.*;
import javax.xml.namespace.*;

import org.morphml.channelml.schema.ChannelmlType;
import org.morphml.morphml.schema.Morphml;
import org.morphml.networkml.schema.Networkml;
import org.morphml.neuroml.schema.Neuroml;

public class NeuroMLConverter
{
	protected JAXBContext jaxb;
	
	protected Marshaller marshaller;
	
	protected Unmarshaller unmarshaller;	
	
	
	public NeuroMLConverter() throws Exception {
		
		jaxb = JAXBContext.newInstance("org.morphml.biophysics.schema:org.morphml.channelml.schema:org.morphml.metadata.schema:org.morphml.morphml.schema:org.morphml.networkml.schema:org.morphml.neuroml.schema");
		
		marshaller = jaxb.createMarshaller();		
		
		marshaller.setProperty( "com.sun.xml.internal.bind.namespacePrefixMapper" , new NeuroMLNamespacePrefixMapper() );
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		unmarshaller = jaxb.createUnmarshaller();
	}
	
	public Neuroml readNeuroML(String xmlFile) throws Exception
	{
		File f = new File(xmlFile);
		
		if (!f.exists()) throw new FileNotFoundException(f.getAbsolutePath());
		
		return (Neuroml) unmarshaller.unmarshal(f);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Morphml xmlToMorphology(String xmlFile) throws Exception
	{
		File f = new File(xmlFile);
		if (!f.exists()) throw new FileNotFoundException(f.getAbsolutePath());
		
		JAXBElement<Morphml> jbe = (JAXBElement<Morphml>) unmarshaller.unmarshal(f);
		
		return jbe.getValue();		
	}
	
	@SuppressWarnings("unchecked")
	public ChannelmlType xmlToChannel(String xmlfile) throws Exception
	{
		File f = new File(xmlfile);
		if (!f.exists()) throw new FileNotFoundException(f.getAbsolutePath());
		
		JAXBElement<ChannelmlType> jbe = (JAXBElement<ChannelmlType>) unmarshaller.unmarshal(f);
		
		return jbe.getValue();
	}
	
	public Networkml xmlToNetwork(String xmlfile) throws Exception
	{
		File f = new File(xmlfile);
		if (!f.exists()) throw new FileNotFoundException(f.getAbsolutePath());
		
		/*JAXBElement<Networkml> jbe =
			(JAXBElement<Networkml>) unmarshaller.unmarshal(f);
		
		return jbe.getValue();*/
		return (Networkml) unmarshaller.unmarshal(f);
	}
	
	public void morphologyToXml(Morphml morph, String filename) throws Exception
	{
		JAXBElement<Morphml> jbm =
			new JAXBElement<Morphml>(new QName("morphml"),
					                    org.morphml.morphml.schema.Morphml.class,
					                    morph);
		File f = new File(filename);
		FileOutputStream fos = new FileOutputStream(f);
				
		marshaller.marshal(jbm, fos);
		fos.close();
	}
	
	public void channelToXml(ChannelmlType chan, String filename) throws Exception {
		JAXBElement<ChannelmlType> jbc =
			new JAXBElement<ChannelmlType>(new QName("channelml"),
										org.morphml.channelml.schema.ChannelmlType.class,
					                    chan);
		File f = new File(filename);
		FileOutputStream fos = new FileOutputStream(f);
				
		marshaller.marshal(jbc, fos);
		fos.close();
	}
	
	public void neuromlToXml(Neuroml l3, String filename) throws Exception
	{
		JAXBElement<Neuroml> jbc = new JAXBElement<Neuroml>(new QName("neuroml"), org.morphml.neuroml.schema.Neuroml.class, l3);
		File f = new File(filename);
		FileOutputStream fos = new FileOutputStream(f);
				
		marshaller.marshal(jbc, fos);
		fos.close();
	}
}
