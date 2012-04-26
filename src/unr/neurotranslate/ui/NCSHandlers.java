package unr.neurotranslate.ui;

import unr.neurotranslate.ui.controller.UIControllerNCS;

public class NCSHandlers {

	public NCSHandlers( final WidgetReferences w, final UIControllerNCS ui ) throws Exception {
	
			new BrainHandler(w, ui);
	
			new ColumnHandler(w, ui);
	
			new LayerHandler(w, ui);
	
			new CellHandler(w, ui);
	
			// new ConnectionHandler(w, ui);
      
			new SynapseHandler(w, ui);
	
			new StimuliHandler(w, ui);
	
			new ReportHandler(w, ui);	
	}	
}
