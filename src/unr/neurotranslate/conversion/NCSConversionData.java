package unr.neurotranslate.conversion;

import unr.neurotranslate.ncs.NCSData;

public class NCSConversionData implements ConversionData {
	
	NCSData ncs;
	
	ConversionNotes notes;

	@Override
	public Object getData() {
		return ncs;
	}

	@Override
	public ConversionNotes getNotes() {
		return notes;
	}
	
	}
