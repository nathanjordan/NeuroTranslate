package unr.neurotranslate.conversion;

import unr.neurotranslate.ncs.NCSData;

public class NCSConversionData implements ConversionData {
	
	NCSData ncs = new NCSData();
	
	ConversionNotes notes = new ConversionNotes();

	@Override
	public Object getData() {
		return ncs;
	}

	@Override
	public ConversionNotes getNotes() {
		return notes;
	}
	
	}
