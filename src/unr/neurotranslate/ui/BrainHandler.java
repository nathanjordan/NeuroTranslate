package unr.neurotranslate.ui;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;

import unr.neurotranslate.ui.controller.UIControllerNCS;

public class BrainHandler {
	
	
	// All array lists are for debugging!
	public ArrayList<String> actBrainColumnList;
	public ArrayList<String> availBrainColumnList;
	public ArrayList<String> brainStimulusList;
	public ArrayList<String> brainConnectionList;
	public ArrayList<String> brainReportsList;
	public ArrayList<String> activeL;		
	public ArrayList<String> availL;		
	
	public BrainHandler( final WidgetReferences w, UIControllerNCS ui ) throws FileNotFoundException {
		
		w.getW("brainScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
					
				
				return false;
			}
		});

		setLists(); 	

		setEntries( w, ui );
		
		modifyHandlers( w, ui );
		
		
	}
	
	public void modifyHandlers( final WidgetReferences w, final UIControllerNCS ui ) {

		// Set up connect for modifying a column
		((Button) w.getW("bModColumns")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
						
				// Show popup and update the views
				w.getW("popup").show();
				ModifyPopup.updateViews( "Column Types", actBrainColumnList, availBrainColumnList, w );		
			}
		});	
		
	
		// Set up connect for modifying a stimulus inject
		((Button) w.getW("bModStimulus")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Show popup and update the views
				w.getW("popup").show();
				ModifyPopup.updateViews( "Stimulus Injects", brainStimulusList, brainStimulusList, w );
				
			}
		});		
	}

	public void setEntries(WidgetReferences w, UIControllerNCS ui) {

		
		
	}

	public void setLists() throws FileNotFoundException {

		// Create array lists
		activeL = new ArrayList<String>();
		availL = new ArrayList<String>();
		actBrainColumnList = new ArrayList<String>();
		availBrainColumnList = new ArrayList<String>();
		brainStimulusList = new ArrayList<String>();
		brainReportsList = new ArrayList<String>();
		brainConnectionList = new ArrayList<String>();
		
		// TODO - Get array lists from data model
		availBrainColumnList.add("visual_cortex");
		availBrainColumnList.add("hypothalamus");
		availBrainColumnList.add("parietal_cortex");		

		brainStimulusList.add("visual");
		brainStimulusList.add("proprioceptive");	
	
		brainReportsList.add("hypothalamus_learning");
		brainReportsList.add("visual_voltage");
		brainReportsList.add("parietal_voltage");
	
		brainConnectionList.add("visual_hypothalamus");
		brainConnectionList.add("visual_parietal");	
		brainConnectionList.add("parietal_hypothalamus");
	}

}