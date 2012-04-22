package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;

import unr.neurotranslate.ncs.Brain;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class BrainHandler {
	
	public Brain currentBrain;
	
	public BrainHandler( final WidgetReferences w, final UIControllerNCS ui ) throws FileNotFoundException {
		
		System.out.println(ui.getColumns());
		
		w.getW("brainScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes								
				//w.getL("bColTypes").setAvailable( ui.getColumns() );		
				//w.getL("bStimInjects").setAvailable( ui.getStimulusInjects() );
				//w.getL("bReports").setAvailable( ui.getReports() );
				w.getL("bColTypes").listToModel(ui.getColumns());
				
				return false;
			}
		});

		
		w.getL("bColTypes").setAvailable( ui.getColumns() );
		
		// grab current brain pointer
		currentBrain = ui.getBrain();
		
		setEntries( w, ui );
		
		modifyHandlers( w, ui );		
	}
	
	public void setEntries( WidgetReferences w, UIControllerNCS ui ) {

		((Entry) w.getW("bType")).setText( currentBrain.type );
		((Entry) w.getW("bJob")).setText( currentBrain.job );
		((Entry) w.getW("bFSV")).setText( currentBrain.FSV.toString() );
		((Entry) w.getW("bDur")).setText( currentBrain.duration.toString() );
		((Entry) w.getW("bSeed")).setText( currentBrain.seed.toString() );
		
	}
	
	public void modifyHandlers( final WidgetReferences w, final UIControllerNCS ui ) {

		// Brain Type
		((Entry)w.getW("bType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {			
				currentBrain.type = arg0.getText();
			}
		});
		
		// Brain Job
		((Entry)w.getW("bJob")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {			
				currentBrain.job = arg0.getText();
			}
		});
		
		
		// Brain FSV
		((Entry)w.getW("bFSV")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {	
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentBrain.FSV = d;
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		
		// Brain Duration
		((Entry)w.getW("bDur")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {			
			
				try {
					double d = Double.parseDouble(arg0.getText());
					currentBrain.duration = d;
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		
		// Brain Seed
		((Entry)w.getW("bSeed")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {			
			
				try {
					int d = Integer.parseInt(arg0.getText());
					currentBrain.seed = d;
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		
		// Set up connect for modifying a column
		((Button) w.getW("bModColumns")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
						
				// Show popup and update the views
				//w.getW("popup").show();
				//ModifyPopup.updateViews( "Column Types", w.getL("bColTypes").getActive(), w.getL("bColTypes").getAvailable(), "bColTypes", w );
				//System.out.println(w.getL("bColTypes").getAvailable());
			}
		});	
		
	
		// Set up connect for modifying a stimulus inject
		((Button) w.getW("bModStimulus")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Show popup and update the views
				//w.getW("popup").show();
				//ModifyPopup.updateViews( "Stimulus Injects", brainStimulusList, brainStimulusList, w );
				
			}
		});		
	}
}