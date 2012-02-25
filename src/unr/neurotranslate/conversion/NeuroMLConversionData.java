package unr.neurotranslate.conversion;

import org.morphml.neuroml.schema.Neuroml;

public class NeuroMLConversionData implements ConversionData {
	
	Neuroml neuroml;
	
	ConversionNotes notes;

	@Override
	public Object getData() {
		return neuroml;
	}

	@Override
	public ConversionNotes getNotes() {
		return notes;
	}
	
	}
