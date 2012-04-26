package unr.neurotranslate.ui;

import unr.neurotranslate.ui.controller.UIControllerNeuroML;

public class NeuroMLHandlers {

	public NeuroMLHandlers(WidgetReferences w, UIControllerNeuroML ui) {
		
		   new MorphologyHandler( w, ui );
		   
		   new ChannelHandler( w, ui );
		   
		   new NetworkHandler( w, ui );
	}
}
