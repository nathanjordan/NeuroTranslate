package unr.neurotranslate.model;

import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncs.NCSData;

public class Data {
	
	private static Data instance = null;
	
	protected Data() {
		
		nml = new Neuroml();
		
		ncs = new NCSData();
		
		}
	
	public static Data getInstance() {
		
		if( instance == null ) {
			
			instance = new Data();
			
			}
		
		return instance;
		
		}
	
	public Neuroml nml;
	
	public NCSData ncs;
	
	}
