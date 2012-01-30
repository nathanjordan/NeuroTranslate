package unr.neurotranslate.util;

import java.util.*;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

/**
 * 
 * @author njordan
 * 
 * Maps the NeuroML object names to XML namespaces ( eg. net: , chan: , net: )
 * 
 */
public class NeuroMLNamespacePrefixMapper extends NamespacePrefixMapper {
	
	protected Map<String, String> namespaceToPrefixMap;
	
	/**
	 * 
	 * Creates the mapping between the namespaces
	 * 
	 */
	public NeuroMLNamespacePrefixMapper() {		
		
		namespaceToPrefixMap = new HashMap<String, String>();
		
		namespaceToPrefixMap.put("http://morphml.org/metadata/schema", "meta");
		namespaceToPrefixMap.put("http://morphml.org/morphml/schema", "mml");
		namespaceToPrefixMap.put("http://morphml.org/channelml/schema", "chan");
		namespaceToPrefixMap.put("http://morphml.org/biophysics/schema", "bio");
		namespaceToPrefixMap.put("http://morphml.org/channelml/schema", "chan");
		namespaceToPrefixMap.put("http://morphml.org/neuroml/schema", "nml");
		namespaceToPrefixMap.put("http://morphml.org/networkml/schema", "net");
		
		}
	
	/**
	 * @author njordan
	 * @param String
	 * @param String
	 * @param boolean
	 * 
	 * Maps the prefixes
	 * 
	 */
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		
		if (namespaceToPrefixMap.containsKey(namespaceUri))
			return namespaceToPrefixMap.get(namespaceUri);
		
		return suggestion;
		
		}
	
	}
