package unr.neurotranslate.model;

import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncsclasses.Arrays;

/**
 * 
 * @author njordan
 * 
 * Abstracts the data thats loaded into the program by the IO controller
 * 
 * Implements the singleton pattern to ensure only one instance exists
 * 
 */
public class DataModel {
	
	private static final DataModel instance = new DataModel();
	
	
	private Arrays _ncsData;
	private Neuroml _neuroData;
	
	private DataModel() { }
	
	/**
	 * 
	 * @return An instance to the data model
	 * 
	 * Uses singleton pattern to return a reference to the object
	 * 
	 */
	public static DataModel getInstance() {
		
        return instance;
        
		}
	
	/**
	 * 
	 * @return returns true if a file is loaded
	 * 
	 */
	public boolean fileLoaded() {
		
		return ( _ncsData == null && _neuroData == null ) ? false : true;
		
		}
	
	/**
	 * 
	 * @return returns true if an NCS file is loaded
	 * 
	 */
	public boolean ncsLoaded() {
		
		return ( _ncsData == null ) ? false : true;
		
		}
	
	/**
	 * 
	 * @return Returns true if a NeuroML file is loaded
	 * 
	 */
	public boolean neuroLoaded() {
		
		return ( _neuroData == null ) ? false : true;
		
		}
	
	
	/**
	 * 
	 * Loads a neuroML file into the object
	 * 
	 */
	public void loadNCSFile() {
		
		}
	
	}
