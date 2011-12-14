package unr.neurotranslate.model;

import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncsclasses.Arrays;

public class DataModel {
	
	private static final DataModel instance = new DataModel();
	
	private Arrays _ncsData;
	private Neuroml _neuroData;
	
	private DataModel() { }
	
	public static DataModel getInstance() {
		
        return instance;
        
		}
	
	public boolean fileLoaded() {
		
		return ( _ncsData == null && _neuroData == null ) ? false : true;
		
		}
	
	public boolean ncsLoaded() {
		
		return ( _ncsData == null ) ? false : true;
		
		}
	
	public boolean neuroLoaded() {
		
		return ( _neuroData == null ) ? false : true;
		
		}
	
	public void loadNCSFile() {
		
		}
	
	}
